package org.graphiks.kextract.pipeline

import org.graphiks.kextract.cli.DllMap
import org.graphiks.kextract.callbacks.CallbackBindingsConfig

/**
 * Immutable configuration snapshot passed through the generation pipeline.
 */
data class Options(
    val clangArgs: List<String> = emptyList(),
    val libraries: List<Library> = emptyList(),
    val useSystemLoadLibrary: Boolean = false,
    val targetPackage: String = "",
    val outputDir: String = ".",
    val jvmNativeResourcesDir: String? = null,
    val jvmNativeLibraries: List<Library> = emptyList(),
    val sharedClassName: String? = null,
    val splitOutput: Boolean = false,
    val variadicArgs: Map<String, Int> = emptyMap(),
    val includeFrameworks: List<String> = emptyList(),
    val includeHelper: IncludeHelper = IncludeHelper(),
    val win32Mode: Boolean = false,
    val dllMap: DllMap? = null,
    val useInitMethod: Boolean = false,
    val multiplatform: Boolean = false,
    val callbackBindings: CallbackBindingsConfig? = null,
    /**
     * When true, function-pointer typedefs whose return type is not `void` are emitted as
     * raw functional interfaces instead of the analyzed safe-callback model. They are never
     * registered as helpers, so a header with non-void callbacks (for example Dawn's cache
     * callbacks returning `size_t`) can still be generated. Callbacks explicitly referenced
     * by the binding configuration must still return `void`.
     */
    val allowNonVoidCallbacks: Boolean = false,
    /**
     * When true, only declarations whose source file lives under the directory of one of
     * the provided headers are generated. System declarations pulled in transitively (for
     * example through `<math.h>`) are dropped.
     */
    val restrictToHeaderPaths: Boolean = false,
    /** Objective-C protocols for which a pointer-backed receiver adapter is generated. */
    val objcProtocolReceivers: Set<String> = emptySet(),
) {
    /** A shared library descriptor. */
    data class Library(val libSpec: String, val specKind: SpecKind) {
        enum class SpecKind { NAME, PATH }

        companion object {
            fun parse(optionString: String): Library {
                val specKind = if (optionString.startsWith(":")) SpecKind.PATH else SpecKind.NAME
                return if (specKind == SpecKind.PATH) {
                    if (optionString.length == 1) throw IllegalArgumentException("Empty library specifier")
                    Library(optionString.substring(1), specKind)
                } else {
                    Library(optionString, specKind)
                }
            }

            fun toQuotedName(lib: Library): String = lib.libSpec.replace("\\", "\\\\")
        }
    }
}
