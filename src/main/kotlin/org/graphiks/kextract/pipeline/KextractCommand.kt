package org.graphiks.kextract.pipeline

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.ProgramResult
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import org.graphiks.kextract.callbacks.CallbackBindingsLoader
import org.graphiks.kextract.cli.DllMap
import java.nio.file.Path

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

    val jvmNativeResourcesDir by option(
        "--jvm-native-resources",
        metavar = "DIR",
        help = "Root containing platform native bundles for multiplatform JVM output",
    )

    val jvmNativeLibraries by option(
        "--jvm-native-library",
        metavar = "LIB",
        help = "JVM-only native library load order; defaults to --library",
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
    val inclObjcClass by option("--include-objc-class", metavar = "NAME",
        help = """Only generate bindings for the named ObjC class (repeatable).
                 |Strongly recommended when the input header imports system frameworks
                 |(Foundation, UIKit, AppKit) — without this flag kextract will attempt
                 |to generate bindings for the entire framework, which produces thousands
                 |of files and likely fails. Example: --include-objc-class NSString""".trimMargin()
    ).multiple()
    val inclObjcProto by option("--include-objc-protocol", metavar = "NAME", help = "Include ObjC protocol").multiple()
    val inclObjcCat   by option("--include-objc-category", metavar = "NAME", help = "Include ObjC category").multiple()
    val objcProtocolReceivers by option(
        "--objc-protocol-receiver",
        metavar = "NAME",
        help = "Generate a pointer-backed receiver adapter for an included ObjC protocol",
    ).multiple()

    // ── Misc ─────────────────────────────────────────────────────────────────

    val dumpIncludes by option("--dump-includes", metavar = "FILE",
        help = "Write a --include-* filter file listing all encountered symbols"
    )

    val objc by option("--objc",
        help = "Enable Objective-C parsing mode (-x objective-c -fobjc-arc); macOS only"
    ).flag()

    val splitOutput by option("--split-output",
        help = "Generate one file per ObjC class + separate files for enums/options/types"
    ).flag()

    val variadicArgs by option("--variadic-args", metavar = "NAME:COUNT",
        help = "Number of ADDRESS variadic slots for a function (repeatable, e.g. XCreateIC:11)"
    ).multiple()

    val includeFrameworks by option("--include-framework", metavar = "NAME",
        help = "Include all declarations from the named SDK framework (repeatable)"
    ).multiple()

    val verbose by option("--verbose",
        help = "Show warnings for skipped system declarations (default: suppressed)"
    ).flag()

    val win32Mode by option("--win32",
        help = "Enable Win32 per-DLL symbol lookup mode"
    ).flag()

    val dllMapPath by option("--dll-map", metavar = "FILE",
        help = "YAML file mapping symbols to DLLs (required with --win32)"
    )

    val callbackBindingsPath by option(
        "--callback-bindings",
        metavar = "FILE",
        help = "YAML metadata for generated safe callback bindings",
    )

    val initMethod by option("--init-method",
        help = "Generate init() method instead of eager static initializers"
    ).flag()

    val multiplatform by option("-m", "--multiplatform",
        help = "Generate Kotlin Multiplatform bindings using the kffi runtime",
    ).flag()

    val allowNonVoidCallbacks by option(
        "--allow-non-void-callbacks",
        help = "Emit non-void function-pointer typedefs as raw functional interfaces instead of " +
            "failing callback discovery (callbacks referenced by --callback-bindings must still return void)",
    ).flag()

    val allow64BitScalars by option(
        "--allow-64-bit-scalars",
        help = "Lower C long/unsigned long to a fixed 64-bit carrier in multiplatform C ABI contexts. " +
            "Only valid for 64-bit LP64 targets (desktop/Android 64-bit); not for LLP64 or 32-bit targets",
    ).flag()

    val restrictToHeaderPaths by option(
        "--restrict-to-header-paths",
        help = "Only generate declarations whose source file is under the directory of an input header; " +
            "drops system declarations pulled in transitively",
    ).flag()

    // ── Positional ───────────────────────────────────────────────────────────

    val headers by argument("headers", help = "C/ObjC header files to process").multiple(required = true)

    // ── Run ──────────────────────────────────────────────────────────────────

    override fun run() {
        KextractConfig.verbose = verbose
        KextractConfig.allow64BitScalars = allow64BitScalars

        if (objc && !isMacOSX) logger.warn("kextract.objc.non.macos.warning")

        if (objc && inclObjcClass.isEmpty()) {
            val systemSdkMarkers = listOf("MacOSX", "/usr/include", "/SDK", ".sdk/")
            val hasSystemHeader = headers.any { h -> systemSdkMarkers.any { marker -> h.contains(marker) } }
                || extraClangArgs.any { a -> systemSdkMarkers.any { marker -> a.contains(marker) } }
            if (hasSystemHeader) {
                System.err.println(
                    "Warning: --objc used without --include-objc-class. " +
                    "This may generate thousands of bindings from system headers. " +
                    "Use --include-objc-class <ClassName> to restrict output."
                )
            }
        }

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

        val variadicArgsMap: Map<String, Int> = variadicArgs.associate { arg ->
            val colon = arg.lastIndexOf(':')
            if (colon < 1) throw IllegalArgumentException(
                "Invalid --variadic-args format: '$arg'. Expected NAME:COUNT (e.g. XCreateIC:11)"
            )
            val name = arg.substring(0, colon).trim()
            val countStr = arg.substring(colon + 1).trim()
            val count = countStr.toIntOrNull() ?: throw IllegalArgumentException(
                "Invalid count in --variadic-args '$arg': '$countStr' is not an integer"
            )
            if (count < 1) throw IllegalArgumentException(
                "Invalid count in --variadic-args '$arg': count must be >= 1"
            )
            name to count
        }

        // Validate --win32 + --dll-map
        val dllMap: DllMap? = if (win32Mode) {
            val path = dllMapPath ?: throw IllegalArgumentException("--win32 requires --dll-map <file>")
            loadDllMap(path)
        } else {
            if (dllMapPath != null) throw IllegalArgumentException("--dll-map requires --win32")
            null
        }
        if (callbackBindingsPath != null && !multiplatform) {
            throw IllegalArgumentException("--callback-bindings requires --multiplatform")
        }
        if (jvmNativeResourcesDir != null && !multiplatform) {
            throw IllegalArgumentException("--jvm-native-resources requires --multiplatform")
        }
        if (jvmNativeLibraries.isNotEmpty() && !multiplatform) {
            throw IllegalArgumentException("--jvm-native-library requires --multiplatform")
        }
        if (allowNonVoidCallbacks && !multiplatform) {
            throw IllegalArgumentException("--allow-non-void-callbacks requires --multiplatform")
        }
        if (allow64BitScalars && !multiplatform) {
            throw IllegalArgumentException("--allow-64-bit-scalars requires --multiplatform")
        }
        val callbackBindings = callbackBindingsPath?.let {
            CallbackBindingsLoader.load(Path.of(it))
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
            jvmNativeResourcesDir = jvmNativeResourcesDir,
            jvmNativeLibraries = jvmNativeLibraries.map { Options.Library.parse(it) },
            sharedClassName    = symbolsClass,
            splitOutput        = splitOutput,
            variadicArgs       = variadicArgsMap,
            includeFrameworks  = includeFrameworks,
            includeHelper      = includeHelper,
            win32Mode          = win32Mode,
            dllMap             = dllMap,
            useInitMethod      = initMethod,
            multiplatform      = multiplatform,
            callbackBindings   = callbackBindings,
            allowNonVoidCallbacks = allowNonVoidCallbacks,
            restrictToHeaderPaths = restrictToHeaderPaths,
            objcProtocolReceivers = objcProtocolReceivers.toSet(),
        )

        val exitCode = KextractTool(logger).runGeneration(headers, options)
        if (exitCode != KextractTool.SUCCESS) throw ProgramResult(exitCode)
    }
}

private fun loadDllMap(path: String): DllMap {
    val mapper = ObjectMapper(YAMLFactory())
    return mapper.readValue(java.nio.file.Path.of(path).toFile(), DllMap::class.java)
}
