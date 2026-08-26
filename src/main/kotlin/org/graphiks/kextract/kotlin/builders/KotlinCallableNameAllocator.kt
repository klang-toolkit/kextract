package org.graphiks.kextract.kotlin.builders

/**
 * Allocates callable Kotlin names inside one emission scope.
 *
 * The first raw or synthetic source that maps to a callable signature keeps its legacy Kotlin
 * name. A later source with the same callable name and parameter types receives an
 * identifier-safe suffix containing an injective UTF-8 encoding of that source key. Return types
 * are deliberately not part of the key because Kotlin cannot overload on return type alone.
 */
class KotlinCallableNameAllocator {
    private data class Request(
        val sourceKey: String,
        val legacyBaseName: String,
        val parameterTypes: List<String>,
        val receiver: String?,
    )

    private data class CallableSignature(
        val name: String,
        val parameterTypes: List<String>,
        val receiver: String?,
    )

    private val allocations = mutableMapOf<Request, String>()
    private val owners = mutableMapOf<CallableSignature, Request>()

    fun allocate(
        selector: String,
        legacyName: String,
        parameterTypes: List<String>,
        receiver: String? = null,
    ): String = allocateInternal(
        sourceKey = "raw:$selector",
        suffixSource = selector,
        legacyName = legacyName,
        parameterTypes = parameterTypes,
        receiver = receiver,
    )

    /**
     * Allocates a name for generator-created Kotlin code that has no Objective-C selector of its
     * own, such as an NSString convenience overload. [sourceKey] must stay stable across runs.
     */
    fun allocateSynthetic(
        sourceKey: String,
        legacyName: String,
        parameterTypes: List<String>,
        receiver: String? = null,
    ): String = allocateInternal(
        sourceKey = "synthetic:$sourceKey",
        suffixSource = sourceKey,
        legacyName = legacyName,
        parameterTypes = parameterTypes,
        receiver = receiver,
    )

    private fun allocateInternal(
        sourceKey: String,
        suffixSource: String,
        legacyName: String,
        parameterTypes: List<String>,
        receiver: String?,
    ): String {
        val legacyBaseName = legacyName.removeSurrounding("`")
        val request = Request(sourceKey, legacyBaseName, parameterTypes, receiver)
        allocations[request]?.let { return it }

        val suffix = suffixSource.toByteArray(Charsets.UTF_8).joinToString("") { byte ->
            byte.toInt().and(0xff).toString(16).padStart(2, '0')
        }
        var candidate = legacyBaseName
        var collision = 1
        while (true) {
            val signature = CallableSignature(candidate, parameterTypes, receiver)
            val owner = owners[signature]
            if (owner == null || owner == request) {
                owners[signature] = request
                return KotlinObjCClassBuilder.escapeIdentifier(candidate).also { allocations[request] = it }
            }
            candidate = "${legacyBaseName}__objc_$suffix" + if (collision == 1) "" else "_$collision"
            collision++
        }
    }
}
