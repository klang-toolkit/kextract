package org.graphiks.kextract.pipeline

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.ProgramResult
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option

/**
 * Clikt command for kextract.
 * Handles all CLI parsing; delegates generation to [KextractTool].
 */
class KextractCommand(private val logger: Logger) : CliktCommand(name = "kextract") {

    override fun commandHelp(context: Context): String = "Generate Kotlin FFI bindings from C/ObjC headers"

    private val isMacOSX: Boolean = System.getProperty("os.name") == "Mac OS X"

    // ── Output / target ──────────────────────────────────────────────────────

    val outputDir by option("-o", "--output",
        help = "Output directory for generated bindings (default: .)"
    ).default(".")

    val targetPackage by option("-t", "--target-package",
        help = "Target package for generated classes"
    ).default("")

    val symbolsClass by option("--symbols-class-name", metavar = "NAME",
        help = "Class name for the shared symbols class"
    )

    // ── Library linking ──────────────────────────────────────────────────────

    val libraries by option("-l", "--library", metavar = "LIB",
        help = "Library to link against (prefix \":\" for a path, e.g. \":libfoo.so\")"
    ).multiple()

    val systemLoad by option("--use-system-load-library",
        help = "Use System.loadLibrary instead of SymbolLookup.libraryLookup"
    ).flag()

    // ── Clang pass-through ───────────────────────────────────────────────────

    val includePaths by option("-I", "--include-path", metavar = "PATH",
        help = "Add a directory to the clang include search path"
    ).multiple()

    val defines by option("-D", metavar = "NAME[=VALUE]",
        help = "Add a preprocessor define passed to clang"
    ).multiple()

    val extraClangArgs by option("-A", "--clang-arg", metavar = "ARG",
        help = "Extra argument forwarded verbatim to clang"
    ).multiple()

    // ── Include filters ──────────────────────────────────────────────────────

    val inclFunctions by option("--include-function",      metavar = "NAME", help = "Include function").multiple()
    val inclVariables by option("--include-var",           metavar = "NAME", help = "Include variable").multiple()
    val inclConstants by option("--include-constant",      metavar = "NAME", help = "Include constant").multiple()
    val inclStructs   by option("--include-struct",        metavar = "NAME", help = "Include struct").multiple()
    val inclUnions    by option("--include-union",         metavar = "NAME", help = "Include union").multiple()
    val inclTypedefs  by option("--include-typedef",       metavar = "NAME", help = "Include typedef").multiple()
    val inclObjcClass by option("--include-objc-class",    metavar = "NAME", help = "Include ObjC class").multiple()
    val inclObjcProto by option("--include-objc-protocol", metavar = "NAME", help = "Include ObjC protocol").multiple()
    val inclObjcCat   by option("--include-objc-category", metavar = "NAME", help = "Include ObjC category").multiple()

    // ── Misc ─────────────────────────────────────────────────────────────────

    val dumpIncludes by option("--dump-includes", metavar = "FILE",
        help = "Write a --include-* filter file listing all encountered symbols"
    )

    val objc by option("--objc",
        help = "Enable Objective-C parsing mode (-x objective-c -fobjc-arc); macOS only"
    ).flag()

    // ── Positional ───────────────────────────────────────────────────────────

    val headers by argument("headers", help = "C/ObjC header files to process").multiple(required = true)

    // ── Run ──────────────────────────────────────────────────────────────────

    override fun run() {
        if (objc && !isMacOSX) logger.warn("kextract.objc.non.macos.warning")

        val includeHelper = IncludeHelper().also { h ->
            dumpIncludes?.let { h.dumpIncludesFile = it }
            inclFunctions.forEach { h.addSymbol(IncludeHelper.IncludeKind.FUNCTION,       it) }
            inclVariables.forEach { h.addSymbol(IncludeHelper.IncludeKind.VAR,            it) }
            inclConstants.forEach { h.addSymbol(IncludeHelper.IncludeKind.CONSTANT,       it) }
            inclStructs.forEach   { h.addSymbol(IncludeHelper.IncludeKind.STRUCT,         it) }
            inclUnions.forEach    { h.addSymbol(IncludeHelper.IncludeKind.UNION,          it) }
            inclTypedefs.forEach  { h.addSymbol(IncludeHelper.IncludeKind.TYPEDEF,        it) }
            inclObjcClass.forEach { h.addSymbol(IncludeHelper.IncludeKind.OBJC_CLASS,     it) }
            inclObjcProto.forEach { h.addSymbol(IncludeHelper.IncludeKind.OBJC_PROTOCOL,  it) }
            inclObjcCat.forEach   { h.addSymbol(IncludeHelper.IncludeKind.OBJC_CATEGORY,  it) }
        }

        val options = Options(
            clangArgs = buildList {
                includePaths.forEach { add("-I$it") }
                defines.forEach     { add("-D$it") }
                addAll(extraClangArgs)
                if (objc && isMacOSX) { add("-x"); add("objective-c"); add("-fobjc-arc") }
            },
            libraries          = libraries.map { Options.Library.parse(it) },
            useSystemLoadLibrary = systemLoad,
            targetPackage      = targetPackage,
            outputDir          = outputDir,
            sharedClassName    = symbolsClass,
            includeHelper      = includeHelper
        )

        val exitCode = KextractTool(logger).runGeneration(headers, options)
        if (exitCode != KextractTool.SUCCESS) throw ProgramResult(exitCode)
    }
}
