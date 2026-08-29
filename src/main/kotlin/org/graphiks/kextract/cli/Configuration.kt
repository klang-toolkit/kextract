package org.graphiks.kextract.cli

import java.nio.file.Path

/**
 * Mutable configuration holder for the kextract CLI.
 * Populated by the CLI argument parser before being passed to the pipeline.
 */
class Configuration {
    var win32Mode: Boolean = false
    var dllMapPath: Path? = null
    var dllMap: DllMap? = null
}

/**
 * Describes the set of symbols to extract from a single DLL.
 * Loaded from the YAML file passed via --dll-map.
 */
data class DllEntry @JvmOverloads constructor(
    val functions: List<String> = emptyList(),
    val structs: List<String> = emptyList(),
    val constants: List<String> = emptyList(),
    /** Exported scalar globals resolved through this DLL's [java.lang.foreign.SymbolLookup]. */
    val variables: List<String> = emptyList(),
)

/**
 * The full DLL → symbols mapping, keyed by DLL filename (e.g. "user32.dll").
 */
data class DllMap(
    val dllMap: Map<String, DllEntry> = emptyMap()
)
