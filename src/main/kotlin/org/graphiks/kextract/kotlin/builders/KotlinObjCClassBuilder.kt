package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.kotlin.utils.TypeMapper

/**
 * Generates a Kotlin class wrapper for an Objective-C @interface declaration.
 *
 * Example output for `@interface NSString : NSObject`:
 * ```kotlin
 * open class NSString(val ptr: MemorySegment) {
 *     companion object {
 *         private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSString") }
 *         fun stringWithUTF8String(cString: MemorySegment): MemorySegment { ... }
 *     }
 *     fun length(): Long { ... }
 * }
 * ```
 *
 * When a superclass is also being generated in the same run its name will appear in
 * [generatedClassNames] and the Kotlin class will extend it, passing `ptr` to `super`.
 */
/**
 * Map from class name to its superclass name (only for classes being generated in this run).
 * Built during the TOPLEVEL prescan and used to detect method overrides.
 */
typealias ClassHierarchy = Map<String, String?>

class KotlinObjCClassBuilder(
    private val builder: SourceBuilder,
    private val toplevel: KotlinToplevelBuilder,
    private val generatedClassNames: Set<String> = emptySet(),
    private val classHierarchy: ClassHierarchy = emptyMap(),
    /**
     * Non-skipped Objective-C class declarations, indexed during the TOPLEVEL pre-scan.
     * Their direct instance callables seed child allocators and verify exact-selector overrides.
     */
    private val classCatalogue: Map<String, Declaration.ObjCClass> = emptyMap(),
    /**
     * The name of the ObjC class currently being generated, set at the start of
     * [visitClass] so that [isOverride] can walk the hierarchy.
     */
    private var _className: String = ""
) {
    private val typeLowerer = ObjCTypeLowerer(toplevel)

    fun visitClass(decl: Declaration.ObjCClass) {
        if (Skip.isPresent(decl)) return

        val className = decl.name()
        _className = className
        val superClass = decl.superClass()

        // KDoc header
        builder.appendLine("/**")
        builder.appendLine(" * Kotlin/JVM wrapper for Objective-C class: $className")
        if (superClass != null) builder.appendLine(" * Superclass: $superClass")
        if (decl.protocols().isNotEmpty())
            builder.appendLine(" * Protocols: ${decl.protocols().joinToString()}")
        builder.appendLine(" */")

        // Emit the superclass clause only when the superclass is also being generated in this
        // run (i.e. it is not Skip-marked and therefore present in generatedClassNames).
        // System-framework root classes such as NSObject are typically not generated, so we
        // fall back to a standalone wrapper in that case.
        // Always declare `val ptr` so extension functions (from category builders) can
        // access the underlying MemorySegment via `this.ptr`.
        // Root classes declare `open val` to permit override in subclasses; subclasses
        // must use `override val` to avoid "hides member of supertype" errors.
        val hasSuper = superClass != null && superClass in generatedClassNames
        val superExpr = if (hasSuper) " : $superClass(ptr)" else ""
        val ptrMod = if (hasSuper) "override val" else "open val"
        toplevel.emitPlatformAvailability(builder, decl)
        builder.appendLine("open class $className($ptrMod ptr: MemorySegment)$superExpr {")
        builder.indent()

        // Companion object for class-level methods and the Class reference
        builder.appendLine("companion object {")
        builder.indent()
        // If a library was specified, reference LOOKUP to force it to load
        // before we ask the ObjC runtime for the class (which requires the dylib to be loaded).
        if (toplevel.hasLookup) {
            builder.appendLine("private val _class: MemorySegment by lazy { LOOKUP.let { ObjCRuntime.getClass(\"$className\") } }")
        } else {
            builder.appendLine("private val _class: MemorySegment by lazy { ObjCRuntime.getClass(\"$className\") }")
        }
        builder.appendLine()

        val companionCallableNames = KotlinCallableNameAllocator()
        // Class methods (+) — selectors retain their Objective-C arity so `foo` and `foo:`
        // can emit valid Kotlin overloads.
        val seenClassMethods = LinkedHashSet<String>()
        val uniqueClassMethods = decl.methods()
            .filter { it.isClassMethod() }
            .filter { seenClassMethods.add(toplevel.objcMemberSignatureKey(true, it.selector())) }
        for (method in uniqueClassMethods) {
            emitMethod(method, receiver = "_class", callableNames = companionCallableNames)
        }

        // Class properties are class-object accessors. Emit them inside the companion object so
        // they dispatch through `_class`, while allowing synthetic `+` methods to win when
        // libclang provides them.
        val seenClassProperties = LinkedHashSet<String>()
        val uniqueClassProperties = decl.properties()
            .filter { it.isClassProperty() }
            .filter { seenClassProperties.add(it.name()) }
        for (property in uniqueClassProperties) {
            emitClassProperty(property, seenClassMethods, companionCallableNames)
        }

        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()

        // Collect all selectors already covered by property getter/setter synthesis so that
        // we don't emit a plain method AND a property accessor with the same signature.
        // ObjC synthesises a getter (and optional setter) method for every @property, so
        // the same selector appears in both decl.methods() and decl.properties().
        // Instance methods (-) retain their Objective-C selector arity, while selectors already
        // emitted as property accessors are skipped to avoid duplicate declarations.
        val instanceCallableNames = KotlinCallableNameAllocator()
        reserveInheritedInstanceCallables(instanceCallableNames)
        val uniqueInstanceMethods = directInstanceMethods(decl)
        for (method in uniqueInstanceMethods) {
            emitMethod(method, receiver = "ptr", callableNames = instanceCallableNames)
        }

        // Properties — deduplicate by property name to avoid redeclaring the same getter/setter
        val uniqueProperties = directInstanceProperties(decl)
        for (prop in uniqueProperties) {
            emitProperty(prop, instanceCallableNames)
        }

        // Instance variables — emitted as comments since direct field access is not
        // supported via the Panama FFI (ObjC ivars are not part of the stable ABI).
        val ivars = decl.ivars()
        if (ivars.isNotEmpty()) {
            builder.appendLine()
            builder.appendLine("// ── Instance variables (direct field access not supported via Panama) ──")
            for (ivar in ivars) {
                builder.appendLine("// ivar: ${ivar.name()}: ${TypeMapper.map(ivar.type())}")
            }
        }

        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    internal fun emitMethod(
        method: Declaration.ObjCMethod,
        receiver: String,
        callableNames: KotlinCallableNameAllocator,
    ) {
        val selector = method.selector()
        val params = method.parameters()
        val retType = method.returnType()
        val retLowering = typeLowerer.lower(retType)
        val retKotlin = retLowering.kotlinType
        val retSpelling = method.returnTypeSpelling()

        val paramList = params.mapIndexed { i, p ->
            val pName = escapeIdentifier(p.name().ifEmpty { "arg$i" })
            val pType = typeLowerer.lower(p.type()).kotlinType
            "$pName: $pType"
        }.joinToString(", ")

        val retDecl = if (retKotlin == "Unit") ": Unit" else ": $retKotlin"

        // Emit a KDoc comment when the original ObjC return type carries generic information
        // (e.g. "NSArray<NSString *> *") that is erased to MemorySegment in the Kotlin binding.
        if (retSpelling.contains('<')) {
            builder.appendLine("/** @return $retSpelling */")
        }
        // Instance methods (receiver == "ptr") are open to allow override in subclasses;
        // class methods in the companion object remain non-open.
        val parameterTypes = params.map { typeLowerer.lower(it.type()).kotlinType }
        val jvmSafe = methodCallableBaseName(method)
        val fnName = callableNames.allocate(
            selector,
            jvmSafe,
            parameterTypes,
        )
        val isMethodOverride = receiver == "ptr" && isOverride(selector, parameterTypes, retKotlin)
        val openMod = if (receiver == "ptr") {
            if (isMethodOverride) "override " else "open "
        } else {
            ""
        }
        toplevel.emitPlatformAvailability(builder, method)
        builder.appendLine("${openMod}fun $fnName($paramList)$retDecl {")
        builder.indent()
        builder.appendLine("val sel = ObjCRuntime.sel(\"$selector\")")

        val argsList = params.mapIndexed { i, p ->
            val pName = escapeIdentifier(p.name().ifEmpty { "arg$i" })
            typeLowerer.lower(p.type()).lowerArgument(pName)
        }.joinToString(", ")
        val argsExpr = if (argsList.isEmpty()) "" else ", $argsList"

        val invocation = retLowering.invocation(receiver, "sel", argsExpr)
        builder.appendLine(if (retLowering.isVoid) invocation else "return $invocation")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()

        // Emit String convenience overloads for NSString parameters / return type
        emitNSStringMethodOverloads(method, receiver, fnName, isMethodOverride, callableNames)
    }

    /**
     * Emits convenience overloads when a method has NSString parameters or returns NSString.
     *
     * Up to three overloads may be generated (in addition to the raw base method):
     *
     * 1. **Return-type overload** (`AsString` suffix) — forwards all params as-is (MemorySegment)
     *    and wraps the NSString return value:
     *    `fun fooAsString(p: MemorySegment): String = ObjCRuntime.toJavaString(foo(p))`
     *
     * 2. **Parameter overload** — replaces each NSString param with `String` and wraps it:
     *    `fun foo(p: String): MemorySegment = foo(ObjCRuntime.newNSString(Arena.global(), p))`
     *
     * 3. **Combined overload** (only when both conditions hold) — String params + String return:
     *    `fun fooAsString(p: String): String = ObjCRuntime.toJavaString(foo(ObjCRuntime.newNSString(...)))`
     *
     * All overloads are skipped when neither condition applies.
     */
    private fun emitNSStringMethodOverloads(
        method: Declaration.ObjCMethod,
        receiver: String,
        fnName: String,
        isOverride: Boolean,
        callableNames: KotlinCallableNameAllocator,
    ) {
        val params = method.parameters()
        // If the base method overrides a parent, the NSString convenience overloads
        // are inherited via polymorphic dispatch — skip regenerating them.
        if (receiver == "ptr" && isOverride) return

        val convenienceNames = allocateNSStringMethodConveniences(method, fnName, callableNames) ?: return
        val nsStringReturnType = convenienceNames.returnAsString != null
        val nsStringParams = params.map { isNSString(it.type()) }
        val hasNSStringParam = convenienceNames.stringParameters != null

        // Raw param list — MemorySegment for NSString params (same as base method)
        val rawParamList = params.mapIndexed { i, p ->
            val pName = escapeIdentifier(p.name().ifEmpty { "arg$i" })
            "$pName: ${typeLowerer.lower(p.type()).kotlinType}"
        }.joinToString(", ")
        val rawArgs = params.mapIndexed { i, p -> escapeIdentifier(p.name().ifEmpty { "arg$i" }) }.joinToString(", ")

        // String param list — String for NSString params, MemorySegment for the rest
        val stringParamList = params.mapIndexed { i, p ->
            val pName = escapeIdentifier(p.name().ifEmpty { "arg$i" })
            val pType = if (nsStringParams[i]) "String" else typeLowerer.lower(p.type()).kotlinType
            "$pName: $pType"
        }.joinToString(", ")
        val wrappedArgs = params.mapIndexed { i, p ->
            val pName = escapeIdentifier(p.name().ifEmpty { "arg$i" })
            if (nsStringParams[i]) "ObjCRuntime.newNSString(Arena.global(), $pName)" else pName
        }.joinToString(", ")

        val retKotlin = typeLowerer.lower(method.returnType()).kotlinType
        val retDecl = if (retKotlin == "Unit") ": Unit" else ": $retKotlin"

        // Overload 1: NSString return → AsString suffix, raw (MemorySegment) params
        if (nsStringReturnType) {
            builder.appendLine("/** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */")
            toplevel.emitPlatformAvailability(builder, method)
            builder.appendLine("fun ${convenienceNames.returnAsString}($rawParamList): String = ObjCRuntime.toJavaString($fnName($rawArgs))")
            builder.appendLine()
        }

        // Overload 2: NSString param(s) → String params, original return type
        if (hasNSStringParam) {
            builder.appendLine("/** Convenience overload — accepts Kotlin [String] for NSString parameters. */")
            toplevel.emitPlatformAvailability(builder, method)
            builder.appendLine("fun ${convenienceNames.stringParameters}($stringParamList)$retDecl = $fnName($wrappedArgs)")
            builder.appendLine()

            // Overload 3 (combined): String params + String return — only when return is also NSString
            if (nsStringReturnType) {
                builder.appendLine("/** Convenience overload — [String] parameters and [String] return type. */")
                toplevel.emitPlatformAvailability(builder, method)
                builder.appendLine("fun ${convenienceNames.combinedAsString}($stringParamList): String = ObjCRuntime.toJavaString($fnName($wrappedArgs))")
                builder.appendLine()
            }
        }
    }

    private data class NSStringMethodConvenienceNames(
        val returnAsString: String?,
        val stringParameters: String?,
        val combinedAsString: String?,
    )

    /**
     * Allocates every convenience name in its generated source order. Keeping this allocation
     * separate from emission lets ancestor reservation replay the exact same callable surface.
     */
    private fun allocateNSStringMethodConveniences(
        method: Declaration.ObjCMethod,
        rawName: String,
        callableNames: KotlinCallableNameAllocator,
        receiver: String? = null,
    ): NSStringMethodConvenienceNames? {
        val params = method.parameters()
        val returnsNSString = isNSString(method.returnType())
        val stringParameters = params.map { isNSString(it.type()) }
        val hasStringParameters = stringParameters.any { it }
        if (!returnsNSString && !hasStringParameters) return null

        val rawParameterTypes = params.map { typeLowerer.lower(it.type()).kotlinType }
        val kotlinStringParameterTypes = params.mapIndexed { index, parameter ->
            if (stringParameters[index]) "String" else typeLowerer.lower(parameter.type()).kotlinType
        }
        val selector = method.selector()
        val returnAsString = if (returnsNSString) {
            callableNames.allocateSynthetic(
                "NSStringAsString:$selector",
                "${syntheticConvenienceBaseName(rawName)}AsString",
                rawParameterTypes,
                receiver,
            )
        } else {
            null
        }
        val stringParameterName = if (hasStringParameters) {
            callableNames.allocateSynthetic(
                "NSStringParameters:$selector",
                rawName,
                kotlinStringParameterTypes,
                receiver,
            )
        } else {
            null
        }
        val combinedAsString = if (returnsNSString && hasStringParameters) {
            callableNames.allocateSynthetic(
                "NSStringAsString:$selector",
                "${syntheticConvenienceBaseName(rawName)}AsString",
                kotlinStringParameterTypes,
                receiver,
            )
        } else {
            null
        }
        return NSStringMethodConvenienceNames(returnAsString, stringParameterName, combinedAsString)
    }

    private data class NSStringPropertyConvenienceNames(
        val getterAsString: String,
        val setterString: String?,
    )

    private fun allocateNSStringPropertyConveniences(
        property: Declaration.ObjCProperty,
        getterName: String,
        setterName: String?,
        callableNames: KotlinCallableNameAllocator,
        receiver: String? = null,
    ): NSStringPropertyConvenienceNames {
        val getterAsString = callableNames.allocateSynthetic(
            "NSStringAsString:${property.getterSelector()}",
            "${syntheticConvenienceBaseName(getterName)}AsString",
            emptyList(),
            receiver,
        )
        val setterString = setterName?.let {
            callableNames.allocateSynthetic(
                "NSStringParameters:${property.setterSelector()}",
                it,
                listOf("String"),
                receiver,
            )
        }
        return NSStringPropertyConvenienceNames(getterAsString, setterString)
    }

    /** Removes Kotlin escaping before composing a synthetic identifier such as `whenAsString`. */
    private fun syntheticConvenienceBaseName(name: String): String = name.removeSurrounding("`")

    private fun emitProperty(prop: Declaration.ObjCProperty, callableNames: KotlinCallableNameAllocator) {
        val propName = prop.name()
        val lowering = typeLowerer.lower(prop.type())
        val retKotlin = lowering.kotlinType
        val getter = prop.getterSelector()
        val propTypeSpelling = prop.typeSpelling()

        builder.appendLine("// @property $propName")
        // Emit a KDoc comment when the original ObjC property type carries generic information
        // (e.g. "NSArray<NSString *> *") that is erased to MemorySegment in the Kotlin binding.
        if (propTypeSpelling.contains('<')) {
            builder.appendLine("/** @return $propTypeSpelling */")
        }
        val getterName = callableNames.allocate(getter, kotlinName(getter), emptyList())
        val getterMod = if (isOverride(getter, emptyList(), retKotlin)) "override " else "open "
        val isOvrd = getterMod.startsWith("override")
        toplevel.emitPlatformAvailability(builder, prop)
        builder.appendLine("${getterMod}fun $getterName(): $retKotlin {")
        builder.indent()
        builder.appendLine("val sel = ObjCRuntime.sel(\"$getter\")")
        val invocation = lowering.invocation("ptr", "sel", "")
        builder.appendLine(if (lowering.isVoid) invocation else "return $invocation")
        builder.unindent()
        builder.appendLine("}")

        var setterFnName: String? = null
        if (!prop.isReadOnly()) {
            val setter = prop.setterSelector()
            val paramType = lowering.kotlinType
            val valueExpr = lowering.lowerArgument("value")
            setterFnName = callableNames.allocate(
                setter,
                kotlinName(setter.removeSuffix(":")),
                listOf(paramType),
            )
            val setterMod = if (isOverride(setter, listOf(paramType), "Unit")) "override " else "open "
            toplevel.emitPlatformAvailability(builder, prop)
            builder.appendLine("${setterMod}fun $setterFnName(value: $paramType) {")
            builder.indent()
            builder.appendLine("val sel = ObjCRuntime.sel(\"$setter\")")
            builder.appendLine("ObjCRuntime.msgSend(null, ptr, sel, $valueExpr)")
            builder.unindent()
            builder.appendLine("}")
        }
        builder.appendLine()

        // NSString convenience overloads for properties.
        // If the property is an override, the convenience overloads are inherited from
        // the parent via polymorphic dispatch — skip regenerating them.
        if (isNSString(prop.type()) && !isOvrd) {
            val convenienceNames = allocateNSStringPropertyConveniences(prop, getterName, setterFnName, callableNames)
            // Getter: String overload
            builder.appendLine("/** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */")
            toplevel.emitPlatformAvailability(builder, prop)
            builder.appendLine("open fun ${convenienceNames.getterAsString}(): String = ObjCRuntime.toJavaString($getterName())")
            builder.appendLine()
            // Setter: String overload (only for readwrite properties)
            if (setterFnName != null && convenienceNames.setterString != null) {
                builder.appendLine("/** Convenience overload — accepts Kotlin [String] for the NSString property. */")
                toplevel.emitPlatformAvailability(builder, prop)
                builder.appendLine("open fun ${convenienceNames.setterString}(value: String) = $setterFnName(ObjCRuntime.newNSString(Arena.global(), value))")
                builder.appendLine()
            }
        }
    }

    private fun emitClassProperty(
        prop: Declaration.ObjCProperty,
        emittedClassMethods: MutableSet<String>,
        callableNames: KotlinCallableNameAllocator,
    ) {
        val getter = prop.getterSelector()
        val emitGetter = emittedClassMethods.add(toplevel.objcMemberSignatureKey(true, getter))
        val setter = prop.setterSelector()
        val emitSetter = !prop.isReadOnly() && emittedClassMethods.add(toplevel.objcMemberSignatureKey(true, setter))
        if (!emitGetter && !emitSetter) return

        val lowering = typeLowerer.lower(prop.type())
        val returnKotlin = lowering.kotlinType
        builder.appendLine("// @property (class) ${prop.name()}")
        if (prop.typeSpelling().contains('<')) {
            builder.appendLine("/** @return ${prop.typeSpelling()} */")
        }

        if (emitGetter) {
            val getterName = callableNames.allocate(getter, kotlinName(getter), emptyList())
            toplevel.emitPlatformAvailability(builder, prop)
            builder.appendLine("fun $getterName(): $returnKotlin {")
            builder.indent()
            builder.appendLine("val sel = ObjCRuntime.sel(\"$getter\")")
            val invocation = lowering.invocation("_class", "sel", "")
            builder.appendLine(if (lowering.isVoid) invocation else "return $invocation")
            builder.unindent()
            builder.appendLine("}")
        }

        if (emitSetter) {
            val setterName = callableNames.allocate(
                setter,
                kotlinName(setter.removeSuffix(":")),
                listOf(returnKotlin),
            )
            val value = lowering.lowerArgument("value")
            toplevel.emitPlatformAvailability(builder, prop)
            builder.appendLine("fun $setterName(value: $returnKotlin) {")
            builder.indent()
            builder.appendLine("val sel = ObjCRuntime.sel(\"$setter\")")
            builder.appendLine("ObjCRuntime.msgSend(null, _class, sel, $value)")
            builder.unindent()
            builder.appendLine("}")
        }
        builder.appendLine()
    }

    private data class InstanceCallable(
        val selector: String,
        val legacyName: String,
        val parameterTypes: List<String>,
        val returnType: String,
    )

    /** Direct instance methods after removing property accessor duplicates. */
    private fun directInstanceMethods(decl: Declaration.ObjCClass): List<Declaration.ObjCMethod> {
        val propertySelectors = decl.properties()
            .filterNot { it.isClassProperty() }
            .flatMapTo(mutableSetOf()) { property ->
                buildList {
                    add(property.getterSelector())
                    if (!property.isReadOnly()) add(property.setterSelector())
                }
            }
        val seenSelectors = LinkedHashSet<String>()
        return decl.methods()
            .filterNot { it.isClassMethod() }
            .filter { it.selector() !in propertySelectors }
            .filter { seenSelectors.add(it.selector()) }
    }

    /** Direct instance properties after the generator's existing name-level deduplication. */
    private fun directInstanceProperties(decl: Declaration.ObjCClass): List<Declaration.ObjCProperty> {
        val seenProperties = LinkedHashSet<String>()
        return decl.properties()
            .filterNot { it.isClassProperty() }
            .filter { seenProperties.add(it.name()) }
    }

    /**
     * Replays exactly the direct instance callable order used by [visitClass]. This is the
     * parent-visible Kotlin member surface that must be reserved before a child emits its own
     * members.
     */
    private fun directInstanceCallables(decl: Declaration.ObjCClass): List<InstanceCallable> = buildList {
        for (method in directInstanceMethods(decl)) {
            val parameters = method.parameters().map { typeLowerer.lower(it.type()).kotlinType }
            add(
                InstanceCallable(
                    method.selector(),
                    methodCallableBaseName(method),
                    parameters,
                    typeLowerer.lower(method.returnType()).kotlinType,
                ),
            )
        }
        for (property in directInstanceProperties(decl)) {
            val lowering = typeLowerer.lower(property.type())
            add(
                InstanceCallable(
                    property.getterSelector(),
                    kotlinName(property.getterSelector()),
                    emptyList(),
                    lowering.kotlinType,
                ),
            )
            if (!property.isReadOnly()) {
                add(
                    InstanceCallable(
                        property.setterSelector(),
                        kotlinName(property.setterSelector().removeSuffix(":")),
                        listOf(lowering.kotlinType),
                        "Unit",
                    ),
                )
            }
        }
    }

    /** Reserves direct members of every generated ancestor before emitting this child. */
    private fun reserveInheritedInstanceCallables(callableNames: KotlinCallableNameAllocator) {
        reserveAncestorInstanceCallables(_className, callableNames, receiver = null)
    }

    /**
     * Reserves the complete visible direct-wrapper surface on [className] for an extension
     * receiver. The receiver differs from the wrapper's member scope, but the name allocation
     * must replay its exact parent-first member and NSString-convenience order.
     */
    internal fun reserveVisibleInstanceCallables(
        className: String,
        callableNames: KotlinCallableNameAllocator,
        receiver: String,
    ) {
        reserveAncestorInstanceCallables(className, callableNames, receiver)
        classCatalogue[className]?.let { reserveDirectInstanceCallables(it, callableNames, receiver) }
    }

    /** Reserves direct instance members from generated ancestors, without this class's own API. */
    internal fun reserveInheritedDirectInstanceCallables(
        className: String,
        callableNames: KotlinCallableNameAllocator,
        receiver: String,
    ) = reserveAncestorInstanceCallables(className, callableNames, receiver)

    private fun reserveAncestorInstanceCallables(
        className: String,
        callableNames: KotlinCallableNameAllocator,
        receiver: String?,
    ) {
        val parentName = classHierarchy[className] ?: return
        reserveAncestorInstanceCallables(parentName, callableNames, receiver)
        val parent = classCatalogue[parentName] ?: return
        reserveDirectInstanceCallables(parent, callableNames, receiver)
    }

    /** Replays one class's direct instance emission order, including generated conveniences. */
    private fun reserveDirectInstanceCallables(
        decl: Declaration.ObjCClass,
        callableNames: KotlinCallableNameAllocator,
        receiver: String? = null,
    ) {
        for (method in directInstanceMethods(decl)) {
            val parameterTypes = method.parameters().map { typeLowerer.lower(it.type()).kotlinType }
            val returnType = typeLowerer.lower(method.returnType()).kotlinType
            val rawName = callableNames.allocate(
                method.selector(),
                methodCallableBaseName(method),
                parameterTypes,
                receiver,
            )
            if (!isOverrideFromClass(decl.name(), method.selector(), parameterTypes, returnType)) {
                allocateNSStringMethodConveniences(method, rawName, callableNames, receiver)
            }
        }

        for (property in directInstanceProperties(decl)) {
            val lowering = typeLowerer.lower(property.type())
            val getter = property.getterSelector()
            val getterName = callableNames.allocate(getter, kotlinName(getter), emptyList(), receiver)
            val getterOverrides = isOverrideFromClass(decl.name(), getter, emptyList(), lowering.kotlinType)
            val setterName = if (property.isReadOnly()) {
                null
            } else {
                val setter = property.setterSelector()
                callableNames.allocate(
                    setter,
                    kotlinName(setter.removeSuffix(":")),
                    listOf(lowering.kotlinType),
                    receiver,
                )
            }
            if (isNSString(property.type()) && !getterOverrides) {
                allocateNSStringPropertyConveniences(property, getterName, setterName, callableNames, receiver)
            }
        }
    }

    /**
     * Kotlin override is valid only when an ancestor emits the same raw Objective-C selector
     * with the same Kotlin parameters and return type. Matching a legacy Kotlin name alone can
     * bind an unrelated selector to the parent's Objective-C dispatch and is therefore invalid.
     */
    private fun isOverride(selector: String, parameterTypes: List<String>, returnType: String): Boolean {
        return isOverrideFromClass(_className, selector, parameterTypes, returnType)
    }

    private fun isOverrideFromClass(
        className: String,
        selector: String,
        parameterTypes: List<String>,
        returnType: String,
    ): Boolean {
        var current = classHierarchy[className]
        while (current != null) {
            val parent = classCatalogue[current]
            if (parent != null && directInstanceCallables(parent).any { callable ->
                    callable.selector == selector &&
                        callable.parameterTypes == parameterTypes &&
                        callable.returnType == returnType
                }
            ) {
                return true
            }
            current = classHierarchy[current]
        }
        return false
    }

    /**
     * Returns the legacy class-member base name, retaining the JVM Object-method escape used by
     * regular method emission so inherited reservations replay the exact same allocation input.
     */
    private fun methodCallableBaseName(method: Declaration.ObjCMethod): String {
        val legacyName = kotlinName(method.selector())
        val parameterTypes = method.parameters().map { typeLowerer.lower(it.type()).kotlinType }
        val returnType = typeLowerer.lower(method.returnType()).kotlinType
        return if (legacyName in JVM_OBJECT_METHODS && parameterTypes.isEmpty() && returnType == "Unit") {
            "${legacyName}ObjC"
        } else {
            legacyName
        }
    }

    /**
     * Returns true when [type] represents NSString or NSString*.
     *
     * In practice Foundation headers always use pointer types (`NSString *`), so the
     * libclang type tree is:
     *   Type.Delegated(POINTER) → Type.Delegated(TYPEDEF, name="NSString")
     *
     * We also handle the bare-typedef case just in case.
     */
    private fun isNSString(type: Type): Boolean {
        if (type is Type.Delegated) {
            // Direct typedef: NSString (rare)
            if (type.kind() == Type.Delegated.Kind.TYPEDEF && type.name() == "NSString") return true
            // Pointer to typedef: NSString * (the common case from Foundation headers)
            if (type.kind() == Type.Delegated.Kind.POINTER) {
                val inner = type.type()
                if (inner is Type.Delegated &&
                    inner.kind() == Type.Delegated.Kind.TYPEDEF &&
                    inner.name() == "NSString") return true
            }
        }
        return false
    }

    companion object {
        /** Kotlin hard keywords that cannot be used as identifiers without backtick escaping. */
        private val HARD_KEYWORDS = setOf(
            "package", "import", "class", "interface", "object", "data", "sealed",
            "fun", "val", "var", "typealias", "constructor", "by", "get", "set", "where",
            "if", "else", "when", "try", "catch", "finally", "for", "while", "do", "continue",
            "break", "return", "throw", "is", "in", "as", "this", "super", "null", "true", "false"
        )

        /** Wraps a Kotlin hard keyword in backticks so it can be used as an identifier. */
        fun escapeIdentifier(name: String): String =
            if (name in HARD_KEYWORDS) "`$name`" else name

        /** java.lang.Object methods that cause "Accidental override" at the JVM level. */
        private val JVM_OBJECT_METHODS = setOf("wait", "notify", "notifyAll", "getClass", "hashCode", "equals", "toString", "clone", "finalize")

        /**
         * Converts an ObjC selector to a valid Kotlin function name.
         * "stringWithUTF8String:" → "stringWithUTF8String"
         * "setLength:" → "setLength"
         */
        fun kotlinName(selector: String): String =
            escapeIdentifier(selector.replace(":", "_").trimEnd('_'))

    }

}
