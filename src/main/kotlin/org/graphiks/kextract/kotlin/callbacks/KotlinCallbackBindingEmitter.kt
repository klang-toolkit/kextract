package org.graphiks.kextract.kotlin.callbacks

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.callbacks.ValidatedCallbackInfoBinding
import org.graphiks.kextract.callbacks.ValidatedDirectFunctionBinding
import org.graphiks.kextract.kotlin.KotlinKmpNamePlan
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_EXCEPTION_HANDLER
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_POLICY
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_REGISTRATION
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_RUNTIME
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.CALLBACK_RUNTIME_API
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.MEMORY_ALLOCATOR
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.NATIVE_ADDRESS
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.OPT_IN
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.SUPPRESS
import org.graphiks.kextract.kotlin.KotlinKmpRuntimeSymbol.UNSAFE_CALLBACK_REARM_API
import org.graphiks.kextract.kotlin.builders.SourceBuilder
import org.graphiks.kextract.kotlin.utils.KotlinIdentifierAllocator

internal class KotlinCallbackBindingEmitter(
    private val mapType: (Type) -> String,
    private val namePlan: KotlinKmpNamePlan,
) {
    fun emitCommon(
        builder: SourceBuilder,
        directBindings: List<KotlinDirectFunctionBindingModel>,
        callbackInfoBindings: List<ValidatedCallbackInfoBinding>,
        callbackModelsByCanonicalId: Map<String, KotlinCallbackModel>,
    ) {
        directBindings.forEach { emitDirectCommon(builder, it, callbackModelsByCanonicalId) }
        callbackInfoBindings.forEach { emitCallbackInfoFactory(builder, it, callbackModelsByCanonicalId) }
    }

    fun emitJvm(
        builder: SourceBuilder,
        bindings: List<KotlinDirectFunctionBindingModel>,
        toRawArgument: (String, Type) -> String,
    ) {
        bindings.forEach { model ->
            val binding = model.binding
            val name = namePlan.declaration(binding.function)
            val parameters = applicationParameters(binding)
            builder.appendLine("@${namePlan.runtime(SUPPRESS)}(\"UNUSED_VARIABLE\")")
            emitPreflightHeader(builder, binding, model.preflightName, parameters, actual = true)
            builder.indent()
            parameters.forEach { parameter ->
                builder.appendLine(
                    "val ${parameter.preparedName} = ${toRawArgument(parameter.name, parameter.variable.type())}",
                )
            }
            builder.appendLine("val address = ${name}_ADDR")
            builder.appendLine("val handle = ${name}_HANDLE")
            builder.appendLine("return { ${preparedCallLambdaParameters(binding)} ->")
            builder.indent()
            builder.appendLine("handle.invokeExact(")
            builder.indent()
            preparedPlatformArguments(binding, parameters, toRawArgument).forEach { argument ->
                builder.appendLine("$argument,")
            }
            builder.unindent()
            builder.appendLine(")")
            builder.unindent()
            builder.appendLine("}")
            builder.unindent()
            builder.appendLine("}")
            builder.appendLine()
        }
    }

    fun emitNative(
        builder: SourceBuilder,
        bindings: List<KotlinDirectFunctionBindingModel>,
        toNativeArgument: (String, Type) -> String,
    ) {
        bindings.forEach { model ->
            val binding = model.binding
            val parameters = applicationParameters(binding)
            emitPreflightHeader(builder, binding, model.preflightName, parameters, actual = true)
            builder.indent()
            parameters.forEach { parameter ->
                builder.appendLine(
                    "val ${parameter.preparedName} = ${toNativeArgument(parameter.name, parameter.variable.type())}",
                )
            }
            builder.appendLine("return { ${preparedCallLambdaParameters(binding)} ->")
            builder.indent()
            builder.appendLine("webgpu.native.${namePlan.rawIdentifier(binding.function)}(")
            builder.indent()
            preparedPlatformArguments(binding, parameters, toNativeArgument).forEach { argument ->
                builder.appendLine("$argument,")
            }
            builder.unindent()
            builder.appendLine(")")
            builder.unindent()
            builder.appendLine("}")
            builder.unindent()
            builder.appendLine("}")
            builder.appendLine()
        }
    }

    fun emitAndroid(
        builder: SourceBuilder,
        bindings: List<KotlinDirectFunctionBindingModel>,
        emitEngineDowncall: (Declaration.Function, (Declaration.Variable) -> String) -> Unit,
    ) {
        bindings.forEach { model ->
            val binding = model.binding
            val parameters = applicationParameters(binding)
            emitPreflightHeader(builder, binding, model.preflightName, parameters, actual = true)
            builder.indent()
            builder.appendLine("return { ${preparedCallLambdaParameters(binding)} ->")
            builder.indent()
            emitEngineDowncall(binding.function) { parameter ->
                when (parameter) {
                    binding.callbackParameter -> "callback"
                    binding.routingUserdataParameter -> "userdata"
                    else -> parameters.single { it.variable === parameter }.name
                }
            }
            builder.unindent()
            builder.appendLine("}")
            builder.unindent()
            builder.appendLine("}")
            builder.appendLine()
        }
    }

    private fun emitDirectCommon(
        builder: SourceBuilder,
        model: KotlinDirectFunctionBindingModel,
        callbackModelsByCanonicalId: Map<String, KotlinCallbackModel>,
    ) {
        val binding = model.binding
        emitPreflightHeader(
            builder,
            binding,
            model.preflightName,
            applicationParameters(binding),
            actual = false,
        )
        builder.appendLine()
        emitDirectRegistrationOverload(builder, binding, model.preflightName, callbackModelsByCanonicalId)
        if (binding.routingUserdataParameter == null) {
            emitDirectRearmOverload(builder, binding, model.preflightName, callbackModelsByCanonicalId)
        }
    }

    private fun emitDirectRegistrationOverload(
        builder: SourceBuilder,
        binding: ValidatedDirectFunctionBinding,
        preflightName: String,
        callbackModelsByCanonicalId: Map<String, KotlinCallbackModel>,
    ) {
        val parameters = applicationParameters(binding)
        val callbackType = callbackModelsByCanonicalId.getValue(binding.callback.id).typeName
        builder.appendLine("@${namePlan.runtime(OPT_IN)}(${namePlan.runtime(CALLBACK_RUNTIME_API)}::class)")
        builder.appendLine("fun ${namePlan.declaration(binding.function)}(")
        builder.indent()
        parameters.forEach { parameter ->
            builder.appendLine("${parameter.name}: ${mapType(parameter.variable.type())},")
        }
        emitRegistrationParameters(builder, callbackType)
        builder.unindent()
        builder.appendLine("): ${namePlan.runtime(CALLBACK_REGISTRATION)}<$callbackType> {")
        builder.indent()
        builder.appendLine("val preparedCall = ${preflightCall(preflightName, parameters)}")
        builder.appendLine("val prepared = $callbackType.prepare(")
        builder.indent()
        builder.appendLine("policy = policy,")
        builder.appendLine("onError = onError,")
        builder.appendLine("callback = callback,")
        builder.unindent()
        builder.appendLine(")")
        builder.appendLine("return ${namePlan.runtime(CALLBACK_RUNTIME)}.activateForNativeCall(prepared) { registration ->")
        builder.indent()
        builder.appendLine(preparedCallInvocation(binding, "registration"))
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitDirectRearmOverload(
        builder: SourceBuilder,
        binding: ValidatedDirectFunctionBinding,
        preflightName: String,
        callbackModelsByCanonicalId: Map<String, KotlinCallbackModel>,
    ) {
        val parameters = applicationParameters(binding)
        val callbackType = callbackModelsByCanonicalId.getValue(binding.callback.id).typeName
        builder.appendLine("@${namePlan.runtime(UNSAFE_CALLBACK_REARM_API)}")
        builder.appendLine("fun rearmAfterNativeQuiescence(")
        builder.indent()
        parameters.forEach { parameter ->
            builder.appendLine("${parameter.name}: ${mapType(parameter.variable.type())},")
        }
        emitRegistrationParameters(builder, callbackType)
        builder.unindent()
        builder.appendLine("): ${namePlan.runtime(CALLBACK_REGISTRATION)}<$callbackType> {")
        builder.indent()
        builder.appendLine("val preparedCall = ${preflightCall(preflightName, parameters)}")
        builder.appendLine("val registration = $callbackType.rearmAfterNativeQuiescence(")
        builder.indent()
        builder.appendLine("policy = policy,")
        builder.appendLine("onError = onError,")
        builder.appendLine("callback = callback,")
        builder.unindent()
        builder.appendLine(")")
        builder.appendLine("try {")
        builder.indent()
        builder.appendLine(preparedCallInvocation(binding, "registration"))
        builder.appendLine("return registration")
        builder.unindent()
        builder.appendLine("} catch (failure: Throwable) {")
        builder.indent()
        builder.appendLine("registration.close()")
        builder.appendLine("throw failure")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitCallbackInfoFactory(
        builder: SourceBuilder,
        binding: ValidatedCallbackInfoBinding,
        callbackModelsByCanonicalId: Map<String, KotlinCallbackModel>,
    ) {
        val structType = namePlan.declaration(binding.struct)
        val callbackType = callbackModelsByCanonicalId.getValue(binding.callback.id).typeName
        val parameterNames = KotlinIdentifierAllocator(RESERVED_PARAMETER_NAMES)
        val applicationUserdataParameters = binding.applicationUserdataFields.mapIndexed { index, field ->
            RenderedCallbackInfoParameter(
                variable = field,
                name = parameterNames.allocate(namePlan.member(field), "arg$index"),
            )
        }
        builder.appendLine("/**")
        builder.appendLine(" * ${binding.owner.lifetime.name}: the owning native call copies the callback-info value or containing descriptor, so the allocator scope may close after the call while the registration remains live.")
        builder.appendLine(" *")
        builder.appendLine(" * This factory does not own [registration].")
        builder.appendLine(" */")
        builder.appendLine("fun $structType.Companion.allocate(")
        builder.indent()
        builder.appendLine("allocator: ${namePlan.runtime(MEMORY_ALLOCATOR)},")
        binding.mode?.let { mode ->
            builder.appendLine("mode: ${namePlan.declaration(mode.type.declaration)},")
        }
        builder.appendLine("registration: ${namePlan.runtime(CALLBACK_REGISTRATION)}<$callbackType>,")
        applicationUserdataParameters.forEach { parameter ->
            builder.appendLine("${parameter.name}: ${namePlan.runtime(NATIVE_ADDRESS)}? = null,")
        }
        builder.unindent()
        builder.appendLine("): $structType {")
        builder.indent()
        binding.mode?.let { mode -> emitModeValidation(builder, mode.allowedConstants.map(namePlan::declaration)) }
        builder.appendLine("val info = allocate(allocator)")
        binding.mode?.let { mode -> builder.appendLine("info.${namePlan.member(mode.field)} = mode") }
        builder.appendLine("info.${namePlan.member(binding.callbackField)} = registration.callback")
        builder.appendLine("info.${namePlan.member(binding.routingUserdataField)} = registration.userdata")
        applicationUserdataParameters.forEach { parameter ->
            builder.appendLine("info.${namePlan.member(parameter.variable)} = ${parameter.name}")
        }
        builder.appendLine("return info")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitModeValidation(builder: SourceBuilder, allowedConstants: List<String>) {
        builder.appendLine("require(")
        builder.indent()
        if (allowedConstants.isEmpty()) {
            builder.appendLine("false,")
        } else {
            allowedConstants.forEachIndexed { index, constant ->
                if (index > 0) builder.indent()
                val suffix = if (index == allowedConstants.lastIndex) "," else " ||"
                builder.appendLine("mode == $constant$suffix")
                if (index > 0) builder.unindent()
            }
        }
        builder.unindent()
        builder.appendLine(")")
    }

    private fun emitRegistrationParameters(builder: SourceBuilder, callbackType: String) {
        val callbackPolicy = namePlan.runtime(CALLBACK_POLICY)
        val callbackExceptionHandler = namePlan.runtime(CALLBACK_EXCEPTION_HANDLER)
        builder.appendLine("policy: $callbackPolicy,")
        builder.appendLine("onError: $callbackExceptionHandler = $callbackExceptionHandler.Default,")
        builder.appendLine("callback: $callbackType,")
    }

    private fun emitPreflightHeader(
        builder: SourceBuilder,
        binding: ValidatedDirectFunctionBinding,
        preflightName: String,
        parameters: List<RenderedParameter>,
        actual: Boolean,
        returnType: String = preparedCallType(binding),
    ) {
        val modifier = if (actual) "actual" else "expect"
        if (parameters.isEmpty()) {
            val suffix = if (actual) " {" else ""
            builder.appendLine("internal $modifier fun $preflightName(): $returnType$suffix")
            return
        }
        builder.appendLine("internal $modifier fun $preflightName(")
        builder.indent()
        parameters.forEach { parameter ->
            builder.appendLine("${parameter.name}: ${mapType(parameter.variable.type())},")
        }
        builder.unindent()
        val suffix = if (actual) " {" else ""
        builder.appendLine("): $returnType$suffix")
    }

    private fun preflightCall(
        preflightName: String,
        applicationParameters: List<RenderedParameter>,
    ): String = "$preflightName(${applicationParameters.joinToString(", ", transform = RenderedParameter::name)})"

    private fun preparedCallInvocation(
        binding: ValidatedDirectFunctionBinding,
        registrationName: String,
    ): String {
        val arguments = buildList {
            add("$registrationName.callback")
            if (binding.routingUserdataParameter != null) add("$registrationName.userdata")
        }
        return "preparedCall(${arguments.joinToString(", ")})"
    }

    private fun preparedPlatformArguments(
        binding: ValidatedDirectFunctionBinding,
        applicationParameters: List<RenderedParameter>,
        convertAddress: (String, Type) -> String,
    ): List<String> {
        val arguments = binding.function.parameters().map { parameter ->
            when {
                parameter === binding.callbackParameter -> convertAddress("callback", parameter.type())
                parameter === binding.routingUserdataParameter -> convertAddress("userdata", parameter.type())
                else -> applicationParameters.single { it.variable === parameter }.preparedName
            }
        }
        return arguments
    }

    private fun applicationParameters(binding: ValidatedDirectFunctionBinding): List<RenderedParameter> {
        val names = KotlinIdentifierAllocator(RESERVED_PARAMETER_NAMES)
        val parameters = binding.function.parameters().mapIndexedNotNull { index, parameter ->
            if (parameter === binding.callbackParameter || parameter === binding.routingUserdataParameter) {
                null
            } else {
                val name = names.allocate(namePlan.parameter(parameter), "arg$index")
                RenderedParameter(
                    variable = parameter,
                    name = name,
                    preparedName = "",
                )
            }
        }
        val preparedNames = KotlinIdentifierAllocator(
            RESERVED_PARAMETER_NAMES + parameters.map(RenderedParameter::name) + PLATFORM_LOCAL_NAMES,
        )
        return parameters.mapIndexed { index, parameter ->
            parameter.copy(
                preparedName = preparedNames.allocate(
                    "prepared${parameter.name.replaceFirstChar(Char::uppercaseChar)}",
                    "preparedArg$index",
                ),
            )
        }
    }

    private fun preparedCallLambdaParameters(binding: ValidatedDirectFunctionBinding): String =
        if (binding.routingUserdataParameter == null) "callback" else "callback, userdata"

    private fun preparedCallType(binding: ValidatedDirectFunctionBinding): String =
        if (binding.routingUserdataParameter == null) {
            "(${namePlan.runtime(NATIVE_ADDRESS)}?) -> Unit"
        } else {
            "(${namePlan.runtime(NATIVE_ADDRESS)}?, ${namePlan.runtime(NATIVE_ADDRESS)}?) -> Unit"
        }

    private data class RenderedParameter(
        val variable: Declaration.Variable,
        val name: String,
        val preparedName: String,
    )

    private data class RenderedCallbackInfoParameter(
        val variable: Declaration.Variable,
        val name: String,
    )

    private companion object {
        val RESERVED_PARAMETER_NAMES = setOf(
            "callback",
            "failure",
            "policy",
            "onError",
            "registration",
            "prepared",
            "preparedCall",
            "allocator",
            "mode",
        )
        val PLATFORM_LOCAL_NAMES = setOf(
            "address",
            "handle",
            "callback",
            "userdata",
        )
    }
}
