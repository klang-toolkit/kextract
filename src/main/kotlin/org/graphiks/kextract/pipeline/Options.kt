package org.graphiks.kextract.pipeline

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
                clangArgs = clangArgs.toList(),
                libraries = libraries.toList(),
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
