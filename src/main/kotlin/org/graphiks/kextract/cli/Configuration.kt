package org.graphiks.kextract.cli

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
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
data class DllEntry(
    val functions: List<String> = emptyList(),
    val structs: List<String> = emptyList(),
    val constants: List<String> = emptyList(),
) {
    private var variableSymbols: List<String> = emptyList()

    /** Exported scalar globals resolved through this DLL's [java.lang.foreign.SymbolLookup]. */
    val variables: List<String>
        get() = variableSymbols

    @JsonCreator
    constructor(
        @JsonProperty("functions") functions: List<String>? = null,
        @JsonProperty("structs") structs: List<String>? = null,
        @JsonProperty("constants") constants: List<String>? = null,
        @JsonProperty("variables") variables: List<String>?,
    ) : this(functions.orEmpty(), structs.orEmpty(), constants.orEmpty()) {
        variableSymbols = variables.orEmpty()
    }

    override fun equals(other: Any?): Boolean =
        other is DllEntry &&
            functions == other.functions &&
            structs == other.structs &&
            constants == other.constants &&
            variables == other.variables

    override fun hashCode(): Int =
        listOf(functions, structs, constants, variables).hashCode()

    override fun toString(): String =
        "DllEntry(functions=$functions, structs=$structs, constants=$constants, variables=$variables)"
}

/**
 * The full DLL → symbols mapping, keyed by DLL filename (e.g. "user32.dll").
 */
data class DllMap(
    val dllMap: Map<String, DllEntry> = emptyMap()
)
