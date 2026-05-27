package org.graphiks.kextract.pipeline

import com.github.ajalt.clikt.core.main
import org.graphiks.kextract.Declaration
import org.graphiks.kextract.kotlin.KotlinGenerator
import org.graphiks.kextract.kotlin.models.KotlinSourceFile
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

/**
 * kextract tool — orchestrates parsing, filtering and code generation.
 * CLI parsing is handled by [KextractCommand] (Clikt).
 */
class KextractTool(private val logger: Logger) {

    companion object {
        val DEBUG: Boolean = System.getProperty("kextract.debug") == "true"
        private val isMacOSX: Boolean = System.getProperty("os.name") == "Mac OS X"

        private val SPECIAL_HEADERS = setOf(
            "stdarg.h", "stddef.h", "stdint.h", "stdbool.h", "stdalign.h",
            "stdnoreturn.h", "stdckdint.h", "stdatomic.h"
        )

        // Exit codes
        const val SUCCESS      = 0
        const val FAILURE      = 1
        const val OPTION_ERROR = 2
        const val INPUT_ERROR  = 3
        const val CLANG_ERROR  = 4
        const val FATAL_ERROR  = 5
        const val OUTPUT_ERROR = 6

        /**
         * Main entry point.
         * Preprocesses GNU-style concatenated options (-DFOO, -I/path) then delegates to Clikt.
         */
        @JvmStatic
        fun main(args: Array<String>) {
            val preprocessed = CommandLine.parse(expandGnuArgs(args.toList()))
            KextractCommand(Logger.DEFAULT).main(preprocessed)
        }

        /**
         * Parse headers directly — used by tests and programmatic callers.
         */
        @JvmStatic
        fun parse(headers: List<String>, vararg parserOptions: String): Declaration.Scoped {
            val source = headers.joinToString("\n") { header ->
                if (isSpecialHeaderName(header)) "#include $header" else "#include \"$header\""
            }
            return Parser(Logger.DEFAULT).parse("kextract\$tmp.h", source, parserOptions.toList())
        }

        /**
         * Expands GNU-style concatenated short options:
         *   -DFOO=1  →  ["-D", "FOO=1"]
         *   -I/path  →  ["-I", "/path"]
         * Other args pass through unchanged.
         */
        @JvmStatic
        fun expandGnuArgs(args: List<String>): List<String> = buildList {
            for (arg in args) {
                when {
                    arg.startsWith("-D") && arg.length > 2 -> { add("-D"); add(arg.substring(2)) }
                    arg.startsWith("-I") && arg.length > 2 -> { add("-I"); add(arg.substring(2)) }
                    else -> add(arg)
                }
            }
        }

        private fun isSpecialHeaderName(header: String): Boolean =
            (header.startsWith("<") && header.endsWith(">")) || header in SPECIAL_HEADERS
    }

    // ── Generation pipeline ───────────────────────────────────────────────────

    /**
     * Parse headers and generate Kotlin bindings according to [options].
     * Returns one of the [SUCCESS]/[FAILURE]/… exit codes.
     */
    fun runGeneration(headers: List<String>, options: Options): Int {
        val outputDir = Path.of(options.outputDir)
        try {
            outputDir.createDirectories()
        } catch (e: Exception) {
            logger.err("kextract.output.dir.create.failed", outputDir.toString(), e.message ?: "")
            return OUTPUT_ERROR
        }

        val decl: Declaration.Scoped = try {
            Parser(logger).parse("kextract\$tmp.h", generateTmpSource(headers), options.clangArgs)
        } catch (e: Exception) {
            logger.err("kextract.parse.failed", e.message ?: "")
            if (DEBUG) e.printStackTrace()
            return FAILURE
        }

        if (DEBUG) {
            System.err.println("Parsed ${decl.members().size} top-level declarations:")
            decl.members().forEach { m -> System.err.println("  ${m.javaClass.simpleName} name=${m.name()}") }
        }

        if (hasObjCDeclarations(decl) && !isMacOSX) {
            logger.warn("kextract.objc.non.macos.warning")
        }

        val results: List<KotlinSourceFile> = try {
            generate(decl, headers[0], options)
        } catch (e: Exception) {
            logger.err("kextract.generation.failed", e.message ?: "")
            return FAILURE
        }

        if (DEBUG) {
            System.err.println("${results.size} result files:")
            results.forEach { r -> System.err.println("  ${r.getPath()}") }
        }

        return writeKotlin(results, outputDir)
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    private fun generate(
        decl: Declaration.Scoped,
        headerName: String,
        options: Options
    ): List<KotlinSourceFile> {
        var d = decl
        d = IncludeFilter(options.includeHelper).scan(d)
        d = DuplicateFilter().scan(d)
        d = UnsupportedFilter(logger).scan(d)
        d = MissingDepChecker(logger).scan(d)
        if (logger.hasErrors()) return emptyList()

        val transformed = NameMangler(headerName).scan(d)
        return KotlinGenerator().generate(transformed, headerName, options.targetPackage)
    }

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

    private fun generateTmpSource(headers: List<String>): String =
        headers.joinToString("\n") { header ->
            if (isSpecialHeaderName(header)) "#include $header" else "#include \"$header\""
        }

    private fun hasObjCDeclarations(decl: Declaration.Scoped): Boolean =
        decl.members().any { it is Declaration.ObjCClass || it is Declaration.ObjCProtocol || it is Declaration.ObjCCategory }
}
