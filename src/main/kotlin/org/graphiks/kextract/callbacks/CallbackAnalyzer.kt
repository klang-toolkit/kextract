package org.graphiks.kextract.callbacks

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import java.util.Collections
import java.util.IdentityHashMap

object CallbackAnalyzer {
    private val userdataName = Regex("userdata(\\d*)")

    fun validate(
        index: CanonicalDeclarationIndex,
        config: CallbackBindingsConfig,
        allowNonVoidCallbacks: Boolean = false,
    ): ValidatedCallbackBindings {
        CallbackBindingsSchemaValidator.validate(config)
        val analyzed = linkedMapOf<String, AnalyzedCallback>()
        fun callback(id: String): AnalyzedCallback = analyzed.getOrPut(id) {
            analyzeCallback(id, index.requireTypedef(id))
        }

        index.typedefIds().forEach { id ->
            val typedef = index.requireTypedef(id)
            val function = exactFunctionPointer(typedef.type()) ?: return@forEach
            // Non-void callbacks can only be validated when explicitly opted in; they are then
            // left to the raw function-pointer generation path rather than the helper model.
            // A non-void callback referenced by the configuration still fails in analyzeCallback.
            if (!isCanonicalVoid(function.returnType()) && allowNonVoidCallbacks) return@forEach
            callback(id)
        }

        val direct = config.directFunctionBindings.map { binding ->
            validateDirect(index, binding, callback(binding.callbackType))
        }
        val infos = config.callbackInfoBindings.map { binding ->
            validateCallbackInfo(index, binding, callback(binding.callbackType))
        }
        return ValidatedCallbackBindings(analyzed.values.toList(), direct, infos)
    }

    fun analyzeCallback(id: String, typedef: Declaration.Typedef): AnalyzedCallback {
        val function = exactFunctionPointer(typedef.type())
            ?: throw CallbackBindingsException(
                "$id: callback typedef must contain exactly one pointer to a function",
            )
        if (!isCanonicalVoid(function.returnType())) {
            throw CallbackBindingsException(
                "$id: callback return type must be void, found ${describeType(function.returnType())}",
            )
        }

        val names = function.parameterNames().orEmpty()
        val parameters = function.argumentTypes().mapIndexed { index, type ->
            CallbackParameter(index, names.getOrElse(index) { "" }, type)
        }
        val candidates = mutableListOf<Pair<Int, CallbackParameter>>()
        for (parameter in parameters) {
            val normalizedName = normalizeName(parameter.name)
            val match = userdataName.matchEntire(normalizedName) ?: continue
            if (!isOpaquePointer(parameter.type)) {
                throw CallbackBindingsException(
                    "$id: parameter '${parameter.name}' is named as userdata but has " +
                        "non-opaque-pointer type ${describeType(parameter.type)}",
                )
            }
            val suffix = match.groupValues[1].ifEmpty { "0" }.toIntOrNull()
                ?: throw CallbackBindingsException(
                    "$id: userdata parameter '${parameter.name}' has an invalid numeric suffix",
                )
            candidates += suffix to parameter
        }
        candidates.groupBy { it.first }.entries.firstOrNull { it.value.size > 1 }?.let { duplicate ->
            val namesAtIndex = duplicate.value.joinToString { "'${it.second.name}'" }
            throw CallbackBindingsException(
                "$id: ambiguous userdata parameters $namesAtIndex normalize to index ${duplicate.key}",
            )
        }

        val routing = candidates.maxByOrNull { it.first }?.second
        val application = candidates
            .asSequence()
            .map { it.second }
            .filterNot { it === routing }
            .sortedBy { it.index }
            .toList()
        return AnalyzedCallback(id, typedef, function, parameters, application, routing)
    }

    private fun validateDirect(
        index: CanonicalDeclarationIndex,
        binding: DirectFunctionBinding,
        callback: AnalyzedCallback,
    ): ValidatedDirectFunctionBinding {
        val function = index.requireFunction(binding.function)
        val callbackParameter = requireParameter(function, binding.callbackParameter, binding.function, "callback")
        val callbackType = canonicalTypedefId(callbackParameter.type())
        if (callbackType != binding.callbackType) {
            throw CallbackBindingsException(
                "${binding.function}: callback parameter '${binding.callbackParameter}' has type " +
                    "${callbackType ?: describeType(callbackParameter.type())}, not ${binding.callbackType}",
            )
        }

        val callbackParameters = function.parameters().filter {
            canonicalTypedefId(it.type()) == binding.callbackType
        }
        if (callbackParameters.size > 1) {
            throw CallbackBindingsException(
                "${binding.function}: callback type ${binding.callbackType} appears in multiple parameters " +
                    callbackParameters.joinToString(prefix = "(", postfix = ")") { it.name() },
            )
        }

        val routing = validateDirectRouting(function, binding, callback, callbackParameter)
        return ValidatedDirectFunctionBinding(function, callbackParameter, callback, routing)
    }

    private fun validateDirectRouting(
        function: Declaration.Function,
        binding: DirectFunctionBinding,
        callback: AnalyzedCallback,
        callbackParameter: Declaration.Variable,
    ): Declaration.Variable? {
        val configuredName = binding.routingUserdataParameter
        val callbackRouting = callback.routingUserdataParameter
        if (configuredName == null) {
            if (callbackRouting != null) {
                throw CallbackBindingsException(
                    "${binding.function}: routingUserdataParameter is required for callback parameter " +
                        "'${callbackRouting.name}'",
                )
            }
            return null
        }
        if (callbackRouting == null) {
            throw CallbackBindingsException(
                "${binding.function}: routing userdata parameter '$configuredName' is configured but " +
                    "${binding.callbackType} has no reserved userdata parameter",
            )
        }

        val routing = requireParameter(function, configuredName, binding.function, "routing userdata")
        if (routing === callbackParameter) {
            throw CallbackBindingsException(
                "${binding.function}: callback and routing userdata parameters must be distinct",
            )
        }
        if (!isOpaquePointer(routing.type())) {
            throw CallbackBindingsException(
                "${binding.function}: routing userdata parameter '$configuredName' has non-opaque-pointer " +
                    "type ${describeType(routing.type())}",
            )
        }
        if (normalizeName(configuredName) != normalizeName(callbackRouting.name)) {
            throw CallbackBindingsException(
                "${binding.function}: routing userdata parameter '$configuredName' does not match " +
                    "the reserved callback parameter '${callbackRouting.name}'",
            )
        }
        if (!sameNormalizedType(routing.type(), callbackRouting.type)) {
            throw CallbackBindingsException(
                "${binding.function}: routing userdata parameter '$configuredName' type does not match " +
                    "${binding.callbackType} parameter '${callbackRouting.name}'",
            )
        }
        return routing
    }

    private fun validateCallbackInfo(
        index: CanonicalDeclarationIndex,
        binding: CallbackInfoBinding,
        callback: AnalyzedCallback,
    ): ValidatedCallbackInfoBinding {
        val struct = index.requireStruct(binding.struct)
        val ownerConfig = binding.owner
            ?: throw CallbackBindingsException("${binding.struct}: owner is required")
        if (ownerConfig.lifetime != CallbackInfoLifetime.CONSUMED_DURING_CALL) {
            throw CallbackBindingsException(
                "${binding.struct}: owner lifetime must be CONSUMED_DURING_CALL",
            )
        }
        val ownerFunction = index.requireFunction(ownerConfig.function)
        val ownerPath = resolveOwnerPath(ownerFunction, ownerConfig.parameterPath, struct, binding.struct)
        val owner = ValidatedCallbackInfoOwner(
            ownerFunction,
            ownerPath,
            CallbackInfoLifetime.CONSUMED_DURING_CALL,
        )

        val callbackField = requireField(struct, binding.callbackField, binding.struct, "callback")
        val actualCallbackType = canonicalTypedefId(callbackField.type())
        if (actualCallbackType != binding.callbackType) {
            throw CallbackBindingsException(
                "${binding.struct}: callback field '${binding.callbackField}' has type " +
                    "${actualCallbackType ?: describeType(callbackField.type())}, not ${binding.callbackType}",
            )
        }

        if (binding.routingUserdataField in binding.applicationUserdataFields) {
            throw CallbackBindingsException(
                "${binding.struct}: routing userdata field '${binding.routingUserdataField}' overlaps " +
                    "application userdata fields",
            )
        }
        duplicate(binding.applicationUserdataFields)?.let {
            throw CallbackBindingsException(
                "${binding.struct}: application userdata field '$it' is configured more than once",
            )
        }

        val callbackRouting = callback.routingUserdataParameter
            ?: throw CallbackBindingsException(
                "${binding.struct}: routing userdata field '${binding.routingUserdataField}' is configured " +
                    "but ${binding.callbackType} has no reserved userdata parameter",
            )
        if (normalizeName(binding.routingUserdataField) != normalizeName(callbackRouting.name)) {
            throw CallbackBindingsException(
                "${binding.struct}: routing userdata field '${binding.routingUserdataField}' does not match " +
                    "the reserved callback parameter '${callbackRouting.name}'",
            )
        }
        val routingField = requireField(struct, binding.routingUserdataField, binding.struct, "routing userdata")
        validateUserdataField(binding.struct, routingField, callbackRouting)

        val expectedApplicationNames = callback.applicationUserdataParameters.map { normalizeName(it.name) }
        val configuredApplicationNames = binding.applicationUserdataFields.map(::normalizeName)
        if (configuredApplicationNames != expectedApplicationNames) {
            throw CallbackBindingsException(
                "${binding.struct}: application userdata fields ${binding.applicationUserdataFields} do not " +
                    "match ${binding.callbackType} parameters " +
                    callback.applicationUserdataParameters.map { it.name },
            )
        }
        val applicationFields = binding.applicationUserdataFields.mapIndexed { indexInList, name ->
            requireField(struct, name, binding.struct, "application userdata").also {
                validateUserdataField(binding.struct, it, callback.applicationUserdataParameters[indexInList])
            }
        }

        val mode = binding.mode?.let { validateMode(index, binding.struct, struct, it) }
        return ValidatedCallbackInfoBinding(
            struct,
            owner,
            callbackField,
            callback,
            routingField,
            applicationFields,
            mode,
        )
    }

    private fun validateMode(
        index: CanonicalDeclarationIndex,
        structId: String,
        struct: Declaration.Scoped,
        config: CallbackInfoMode,
    ): ValidatedCallbackInfoMode {
        val field = requireField(struct, config.field, structId, "mode")
        val type = index.requireCanonicalTypedef(config.type)
        val actualType = canonicalTypedefId(field.type())
        if (actualType != config.type) {
            throw CallbackBindingsException(
                "$structId: mode field '${config.field}' has type " +
                    "${actualType ?: describeType(field.type())}, not ${config.type}",
            )
        }
        duplicate(config.allowedConstants)?.let {
            throw CallbackBindingsException("$it: mode constant is configured more than once for $structId")
        }
        val constants = config.allowedConstants.map { constantId ->
            index.requireConstant(constantId).also { constant ->
                if (!index.belongsToCanonicalTypedef(constant, type)) {
                    val actual = index.describeConstantType(constant)
                    throw CallbackBindingsException(
                        "$constantId: constant type $actual does not match ${config.type} for $structId",
                    )
                }
            }
        }
        return ValidatedCallbackInfoMode(field, type, constants)
    }

    private fun resolveOwnerPath(
        function: Declaration.Function,
        configuredPath: String,
        target: Declaration.Scoped,
        targetId: String,
    ): List<Declaration.Variable> {
        val functionId = "function:${function.name()}"
        val segments = configuredPath.split('.')
        val resolved = mutableListOf<Declaration.Variable>()
        var variable = requireParameter(function, segments.first(), functionId, "owner path")
        resolved += variable
        segments.drop(1).forEach { segment ->
            val scope = structFromType(variable.type())
                ?: throw CallbackBindingsException(
                    "$functionId: owner path '$configuredPath' cannot traverse '${variable.name()}'",
                )
            variable = requireField(scope, segment, functionId, "owner path")
            resolved += variable
        }

        if (!isStructType(variable.type(), target)) {
            val suffixes = findPathsToStruct(variable.type(), target)
            if (suffixes.size > 1) {
                val completePaths = suffixes.map { suffix ->
                    (segments + suffix).joinToString(".")
                }
                throw CallbackBindingsException(
                    "$functionId: owner path '$configuredPath' is ambiguous for $targetId " +
                        completePaths.joinToString(prefix = "(", postfix = ")"),
                )
            }
            if (suffixes.size == 1) {
                val required = (segments + suffixes.single()).joinToString(".")
                throw CallbackBindingsException(
                    "$functionId: owner path '$configuredPath' is incomplete for $targetId; use '$required'",
                )
            }
            throw CallbackBindingsException(
                "$functionId: owner path '$configuredPath' does not resolve to $targetId",
            )
        }
        return resolved
    }

    private fun findPathsToStruct(type: Type, target: Declaration.Scoped): List<List<String>> {
        val visited = Collections.newSetFromMap(IdentityHashMap<Declaration.Scoped, Boolean>())
        fun visit(current: Type): List<List<String>> {
            if (isStructType(current, target)) return listOf(emptyList())
            val scope = structFromType(current) ?: return emptyList()
            if (!visited.add(scope)) return emptyList()
            return scope.members()
                .filterIsInstance<Declaration.Variable>()
                .flatMap { field -> visit(field.type()).map { listOf(field.name()) + it } }
                .also { visited.remove(scope) }
        }
        return visit(type).filter { it.isNotEmpty() }
    }

    private fun requireParameter(
        function: Declaration.Function,
        name: String,
        id: String,
        role: String,
    ): Declaration.Variable {
        val matches = function.parameters().filter { it.name() == name }
        if (matches.isEmpty()) {
            throw CallbackBindingsException("$id: $role parameter '$name' does not exist")
        }
        if (matches.size > 1) {
            throw CallbackBindingsException("$id: $role parameter '$name' is ambiguous")
        }
        return matches.single()
    }

    private fun requireField(
        struct: Declaration.Scoped,
        name: String,
        id: String,
        role: String,
    ): Declaration.Variable {
        val matches = struct.members().filterIsInstance<Declaration.Variable>().filter { it.name() == name }
        if (matches.isEmpty()) {
            throw CallbackBindingsException("$id: $role field '$name' does not exist")
        }
        if (matches.size > 1) {
            throw CallbackBindingsException("$id: $role field '$name' is ambiguous")
        }
        return matches.single()
    }

    private fun validateUserdataField(
        structId: String,
        field: Declaration.Variable,
        callbackParameter: CallbackParameter,
    ) {
        if (!isOpaquePointer(field.type())) {
            throw CallbackBindingsException(
                "$structId: userdata field '${field.name()}' has non-opaque-pointer type " +
                    describeType(field.type()),
            )
        }
        if (!sameNormalizedType(field.type(), callbackParameter.type)) {
            throw CallbackBindingsException(
                "$structId: userdata field '${field.name()}' type does not match callback parameter " +
                    "'${callbackParameter.name}'",
            )
        }
    }

    private fun duplicate(values: List<String>): String? =
        values.groupingBy { it }.eachCount().entries.firstOrNull { it.value > 1 }?.key

    private fun exactFunctionPointer(type: Type) = CallbackTypeResolver.exactFunctionPointer(type)

    private fun isCanonicalVoid(type: Type) = CallbackTypeResolver.isCanonicalVoid(type)

    private fun isOpaquePointer(type: Type) = CallbackTypeResolver.isOpaquePointer(type)

    private fun canonicalTypedefId(type: Type) = CallbackTypeResolver.canonicalTypedefId(type)

    private fun structFromType(type: Type) = CallbackTypeResolver.structFromType(type)

    private fun isStructType(type: Type, target: Declaration.Scoped) =
        CallbackTypeResolver.isStructType(type, target)

    private fun sameNormalizedType(left: Type, right: Type) =
        CallbackTypeResolver.sameNormalizedType(left, right)

    private fun normalizeName(name: String): String =
        name.lowercase().filter(Char::isLetterOrDigit)

    private fun describeType(type: Type) = CallbackTypeResolver.describeType(type)
}
