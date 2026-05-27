/*
 * Copyright (c) 2020, 2025, Oracle and/or its affiliates. All rights reserved.
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

package org.openjdk.kextract.pipeline

import org.openjdk.kextract.Declaration
import org.openjdk.kextract.kotlin.KotlinGenerator
import org.openjdk.kextract.kotlin.models.KotlinSourceFile
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.readLines
import kotlin.io.path.writeText

/**
 * Main entry point for kextract tool - Kotlin version.
 * Fully Kotlin pipeline: Parser → NameMangler → KotlinGenerator.
 */
class KextractTool(private val logger: Logger) {

    companion object {
        val DEBUG: Boolean = System.getProperty("kextract.debug") == "true"
        private val isMacOSX: Boolean = System.getProperty("os.name") == "Mac OS X"

        /** Headers that should be included with angle-brackets or are known special names. */
        private val SPECIAL_HEADERS = setOf(
            "stdarg.h", "stddef.h", "stdint.h", "stdbool.h", "stdalign.h",
            "stdnoreturn.h", "stdckdint.h", "stdatomic.h"
        )

        // Error codes
        @JvmStatic val SUCCESS = 0
        @JvmStatic val FAILURE = 1
        @JvmStatic val OPTION_ERROR = 2
        @JvmStatic val INPUT_ERROR = 3
        @JvmStatic val CLANG_ERROR = 4
        @JvmStatic val FATAL_ERROR = 5
        @JvmStatic val OUTPUT_ERROR = 6

        /**
         * Main entry point.
         */
        @JvmStatic
        fun main(args: Array<String>) {
            System.exit(KextractTool(Logger.DEFAULT).run(args))
        }

        /**
         * Parse and generate.
         */
        @JvmStatic
        fun parse(headers: List<String>, vararg parserOptions: String): Declaration.Scoped {
            val source = headers.joinToString("\n") { header ->
                if (header.startsWith("<") && header.endsWith(">") || header in SPECIAL_HEADERS)
                    "#include $header"
                else
                    "#include \"$header\""
            }
            return Parser(Logger.DEFAULT).parse("kextract\$tmp.h", source, parserOptions.toList())
        }
    }

    /**
     * Main run method.
     */
    fun run(args: Array<String>): Int {
        if (DEBUG) {
            System.err.println("kextract debug mode enabled")
        }

        if (args.isEmpty()) {
            logger.err("kextract.no.headers")
            return FAILURE
        }

        // Parse command line arguments using Options
        val positional = mutableListOf<String>()
        val optionsBuilder = Options.builder()

        try {
            parseArgs(args.toList(), optionsBuilder, positional)
        } catch (e: Exception) {
            logger.err("kextract.option.parse.failed", e.message ?: "")
            return OPTION_ERROR
        }

        val builtOptions = try {
            optionsBuilder.build()
        } catch (e: Exception) {
            logger.err("kextract.option.build.failed", e.message ?: "")
            return OPTION_ERROR
        }

        // Validate options
        if (positional.isEmpty()) {
            logger.err("kextract.no.headers")
            return FAILURE
        }

        val headers = positional.toList()
        val outputDir = Path.of(builtOptions.outputDir)

        try {
            outputDir.createDirectories()
        } catch (e: Exception) {
            logger.err("kextract.output.dir.create.failed", outputDir.toString(), e.message ?: "")
            return OUTPUT_ERROR
        }

        // Parse headers
        val decl: Declaration.Scoped = try {
            Parser(logger).parse("kextract\$tmp.h", generateTmpSource(headers), builtOptions.clangArgs)
        } catch (e: Exception) {
            logger.err("kextract.parse.failed", e.message ?: "")
            if (DEBUG) {
                e.printStackTrace()
            }
            return FAILURE
        }

        if (DEBUG) {
            System.err.println("Parsed ${decl.members().size} top-level declarations:")
            decl.members().forEach { m -> System.err.println("  ${m.javaClass.simpleName} name=${m.name()}") }
        }

        // ObjC guard: warn if ObjC declarations found on a non-macOS platform
        if (hasObjCDeclarations(decl) && !isMacOSX) {
            logger.warn("kextract.objc.non.macos.warning")
        }

        // Generate bindings
        val results = try {
            generate(decl, headers[0], builtOptions.targetPackage, builtOptions.libraries,
                builtOptions.useSystemLoadLibrary, builtOptions.includeHelper, builtOptions.sharedClassName)
        } catch (e: Exception) {
            logger.err("kextract.generation.failed", e.message ?: "")
            return FAILURE
        }

        if (DEBUG) {
            System.err.println("${results.size} result files:")
            results.forEach { r -> System.err.println("  ${r.javaClass.simpleName}") }
        }

        return writeKotlin(results, outputDir)
    }

    /**
     * Parse command line arguments.
     */
    fun parseArgs(
        args: List<String>,
        options: Options.Builder,
        positional: MutableList<String>
    ) {
        val iterator = args.listIterator()
        while (iterator.hasNext()) {
            val arg = iterator.next()
            when {
                arg.startsWith("@") -> {
                    val path = Path.of(arg.substring(1))
                    val fileArgs = path.readLines()
                        .filter { it.isNotBlank() && !it.startsWith("#") }
                        .flatMap { it.trim().split("\\s+".toRegex()) }
                    parseArgs(fileArgs, options, positional)
                }
                arg == "--help" || arg == "-h" -> {
                    printHelp()
                    System.exit(SUCCESS)
                }
                arg == "--version" || arg == "-V" -> {
                    printVersion()
                    System.exit(SUCCESS)
                }
                arg == "--output" || arg == "-o" -> {
                    if (!iterator.hasNext()) throw IllegalArgumentException("Missing argument for $arg")
                    options.setOutputDir(iterator.next())
                }
                arg == "--include-path" || arg == "-I" -> {
                    if (!iterator.hasNext()) throw IllegalArgumentException("Missing argument for $arg")
                    options.addClangArg("-I${iterator.next()}")
                }
                arg.startsWith("-I") -> {
                    options.addClangArg(arg)
                }
                arg == "--library" || arg == "-l" -> {
                    if (!iterator.hasNext()) throw IllegalArgumentException("Missing argument for $arg")
                    val lib = iterator.next()
                    options.addLibrary(Options.Library.parse(lib))
                }
                arg == "--dump-includes" -> {
                    if (!iterator.hasNext()) throw IllegalArgumentException("Missing argument for $arg")
                    options.setDumpIncludeFile(iterator.next())
                }
                arg == "--use-system-load-library" -> {
                    options.setUseSystemLoadLibrary(true)
                }
                arg == "--target-package" || arg == "-t" -> {
                    if (!iterator.hasNext()) throw IllegalArgumentException("Missing argument for $arg")
                    options.setTargetPackage(iterator.next())
                }
                arg == "--header-class-name" -> {
                    if (!iterator.hasNext()) throw IllegalArgumentException("Missing argument for $arg")
                    // Note: Java Options doesn't have setHeaderClassName directly
                    // This is handled differently in Java version
                }
                arg == "--symbols-class-name" -> {
                    if (!iterator.hasNext()) throw IllegalArgumentException("Missing argument for $arg")
                    options.setSharedClassName(iterator.next())
                }
                arg == "--objc" -> {
                    // Force Objective-C parsing mode: treat headers as ObjC and enable ARC
                    if (isMacOSX) {
                        options.addClangArg("-x")
                        options.addClangArg("objective-c")
                        options.addClangArg("-fobjc-arc")
                    } else {
                        logger.warn("kextract.objc.non.macos.warning")
                    }
                }
                arg.startsWith("--include-") -> {
                    val kindName = arg.removePrefix("--include-").uppercase()
                    val kind = IncludeHelper.IncludeKind.entries.firstOrNull { it.name == kindName }
                        ?: throw IllegalArgumentException("Unknown include option: $arg")
                    if (!iterator.hasNext()) throw IllegalArgumentException("Missing argument for $arg")
                    options.addIncludeSymbol(kind, iterator.next())
                }
                arg.startsWith("-D") -> {
                    options.addClangArg(arg)
                }
                arg.startsWith("-") -> {
                    options.addClangArg(arg)
                }
                else -> {
                    positional.add(arg)
                }
            }
        }
    }

    /**
     * Print help message.
     */
    private fun printHelp() {
        println("""
            |Usage: kextract <options> header-file...
            |  where options include:
            |    --help, -h
            |        print this help message
            |    --version, -V
            |        print kextract version
            |    --output <path>, -o <path>
            |        output directory for generated bindings (default: .)
            |    --include-path <path>, -I <path>
            |        add include path for header files
            |    --library <name>, -l <name>
            |        add library to link against
            |    --dump-includes <file>
            |        dump include information to file
            |    --use-system-load-library
            |        use System.loadLibrary for library loading
            |    --target-package <pkg>, -t <pkg>
            |        target package for generated classes
            |    --symbols-class-name <name>
            |        name for the symbols class
            |    --objc
            |        enable Objective-C parsing mode (-x objective-c -fobjc-arc); macOS only
        """.trimMargin())
    }

    /**
     * Print version information.
     */
    private fun printVersion() {
        println("kextract ${getVersion()}")
        println("LibClang: ${LibClang.version()}")
    }

    /**
     * Get kextract version.
     */
    private fun getVersion(): String {
        return KextractTool::class.java.`package`?.implementationVersion ?: "dev"
    }

    /**
     * Generate Kotlin bindings.
     */
    private fun generate(
        decl: Declaration.Scoped,
        headerName: String,
        targetPkg: String,
        libs: List<Options.Library>,
        useSystemLoadLibrary: Boolean,
        includeHelper: IncludeHelper,
        sharedClassName: String? = null
    ): List<KotlinSourceFile> {
        // Run the filter pipeline
        var d: Declaration.Scoped = decl
        d = IncludeFilter(includeHelper).scan(d)
        d = DuplicateFilter().scan(d)
        d = UnsupportedFilter(logger).scan(d)
        d = MissingDepChecker(logger).scan(d)
        if (logger.hasErrors()) return emptyList()

        val transformedDecl = NameMangler(headerName).scan(d)
        return KotlinGenerator().generate(transformedDecl, headerName, targetPkg)
    }

    /**
     * Write Kotlin files.
     */
    private fun writeKotlin(results: List<KotlinSourceFile>, outputDir: Path): Int {
        return try {
            for (result in results) {
                val outputPath = outputDir.resolve(result.getPath())
                outputPath.parent.createDirectories()
                outputPath.writeText(result.contents)
            }
            SUCCESS
        } catch (e: Exception) {
            System.err.println("Error writing Kotlin files: ${e.message}")
            OUTPUT_ERROR
        }
    }

    /**
     * Returns true if any top-level member of [decl] is an ObjC declaration.
     * Used to emit a warning when ObjC bindings are generated on non-macOS platforms.
     */
    private fun hasObjCDeclarations(decl: Declaration.Scoped): Boolean =
        decl.members().any { m ->
            m is Declaration.ObjCClass ||
            m is Declaration.ObjCProtocol ||
            m is Declaration.ObjCCategory
        }

    /**
     * Sanitize header name.
     */
    fun sanitizeClassName(name: String): String {
        val sanitized = name.replace(Regex("[^a-zA-Z0-9_]"), "_")
        return if (sanitized.isNotEmpty() && sanitized[0].isDigit()) "_$sanitized" else sanitized
    }

    /**
     * Generate temporary source for special headers.
     */
    fun generateTmpSource(headers: List<String>): String {
        if (headers.isEmpty()) return ""
        
        return headers.joinToString("\n") { header ->
            if (isSpecialHeaderName(header)) {
                "#include $header"
            } else {
                "#include \"$header\""
            }
        }
    }

    /**
     * Check if header is special (enclosed in <> or is a well-known C standard header).
     */
    fun isSpecialHeaderName(header: String): Boolean =
        (header.startsWith("<") && header.endsWith(">")) || header in SPECIAL_HEADERS

}
