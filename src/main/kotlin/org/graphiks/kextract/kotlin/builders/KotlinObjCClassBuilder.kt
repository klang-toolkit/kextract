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
     * Map from class name to the set of Kotlin method / property accessor signatures
     * declared directly (not inherited) on that class.  Used to detect overrides so that
     * `override` is emitted only when the method exists in a (non-NSObject) superclass.
     */
    private val classMethodSignatures: Map<String, Set<String>> = emptyMap(),
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

        // Class methods (+) — deduplicate by Kotlin name to avoid colliding function signatures
        val seenClassMethods = LinkedHashSet<String>()
        val uniqueClassMethods = decl.methods()
            .filter { it.isClassMethod() }
            .filter { seenClassMethods.add(kotlinName(it.selector())) }
        for (method in uniqueClassMethods) {
            emitMethod(method, receiver = "_class")
        }

        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()

        // Collect all selectors already covered by property getter/setter synthesis so that
        // we don't emit a plain method AND a property accessor with the same signature.
        // ObjC synthesises a getter (and optional setter) method for every @property, so
        // the same selector appears in both decl.methods() and decl.properties().
        val propertySelectors: Set<String> = decl.properties()
            .flatMapTo(mutableSetOf()) { prop ->
                buildList {
                    add(prop.getterSelector())
                    if (!prop.isReadOnly()) add(prop.setterSelector())
                }
            }

        // Instance methods (-) — deduplicate by Kotlin name; skip any selector already emitted
        // as a property accessor to avoid "conflicting overloads" in the generated source.
        val seenInstanceMethods = LinkedHashSet<String>()
        val uniqueInstanceMethods = decl.methods()
            .filter { !it.isClassMethod() }
            .filter { it.selector() !in propertySelectors }
            .filter { seenInstanceMethods.add(kotlinName(it.selector())) }
        for (method in uniqueInstanceMethods) {
            emitMethod(method, receiver = "ptr")
        }

        // Properties — deduplicate by property name to avoid redeclaring the same getter/setter
        val seenProperties = LinkedHashSet<String>()
        val uniqueProperties = decl.properties()
            .filter { seenProperties.add(it.name()) }
        for (prop in uniqueProperties) {
            emitProperty(prop)
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

    internal fun emitMethod(method: Declaration.ObjCMethod, receiver: String) {
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
        val fnName = kotlinName(selector)
        val openMod = if (receiver == "ptr") { if (isOverride(fnName)) "override " else "open " } else ""
        // Rename methods that collide with java.lang.Object methods (e.g. NSCondition.wait()
        // vs Object.wait()) to avoid "Accidental override" at the JVM level.
        val jvmSafe = if (fnName in JVM_OBJECT_METHODS && paramList.isEmpty() && retKotlin == "Unit") "${fnName}ObjC" else fnName
        builder.appendLine("${openMod}fun $jvmSafe($paramList)$retDecl {")
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
        emitNSStringMethodOverloads(method, receiver, fnName)
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
    private fun emitNSStringMethodOverloads(method: Declaration.ObjCMethod, receiver: String, fnName: String) {
        val params = method.parameters()
        val retType = method.returnType()
        val nsStringReturnType = isNSString(retType)
        val nsStringParams = params.map { isNSString(it.type()) }
        val hasNSStringParam = nsStringParams.any { it }

        if (!nsStringReturnType && !hasNSStringParam) return

        // If the base method overrides a parent, the NSString convenience overloads
        // are inherited via polymorphic dispatch — skip regenerating them.
        if (receiver == "ptr" && isOverride(fnName)) return

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

        val retKotlin = typeLowerer.lower(retType).kotlinType
        val retDecl = if (retKotlin == "Unit") ": Unit" else ": $retKotlin"

        // Overload 1: NSString return → AsString suffix, raw (MemorySegment) params
        if (nsStringReturnType) {
            builder.appendLine("/** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */")
            builder.appendLine("fun ${fnName}AsString($rawParamList): String = ObjCRuntime.toJavaString($fnName($rawArgs))")
            builder.appendLine()
        }

        // Overload 2: NSString param(s) → String params, original return type
        if (hasNSStringParam) {
            builder.appendLine("/** Convenience overload — accepts Kotlin [String] for NSString parameters. */")
            builder.appendLine("fun $fnName($stringParamList)$retDecl = $fnName($wrappedArgs)")
            builder.appendLine()

            // Overload 3 (combined): String params + String return — only when return is also NSString
            if (nsStringReturnType) {
                builder.appendLine("/** Convenience overload — [String] parameters and [String] return type. */")
                builder.appendLine("fun ${fnName}AsString($stringParamList): String = ObjCRuntime.toJavaString($fnName($wrappedArgs))")
                builder.appendLine()
            }
        }
    }

    private fun emitProperty(prop: Declaration.ObjCProperty) {
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
        val getterName = kotlinName(getter)
        val getterMod = if (isOverride(getterName)) "override " else "open "
        val isOvrd = getterMod.startsWith("override")
        builder.appendLine("${getterMod}fun $getterName(): $retKotlin {")
        builder.indent()
        builder.appendLine("val sel = ObjCRuntime.sel(\"$getter\")")
        val invocation = lowering.invocation("ptr", "sel", "")
        builder.appendLine(if (lowering.isVoid) invocation else "return $invocation")
        builder.unindent()
        builder.appendLine("}")

        if (!prop.isReadOnly()) {
            val setter = prop.setterSelector()
            val paramType = lowering.kotlinType
            val valueExpr = lowering.lowerArgument("value")
            val setterFnName = kotlinName(setter.removeSuffix(":"))
            val setterMod = if (isOverride(setterFnName)) "override " else "open "
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
            val getterFn = kotlinName(getter)
            // Getter: String overload
            builder.appendLine("/** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */")
            builder.appendLine("open fun ${getterFn}AsString(): String = ObjCRuntime.toJavaString($getterFn())")
            builder.appendLine()
            // Setter: String overload (only for readwrite properties)
            if (!prop.isReadOnly()) {
                val setterFn = kotlinName(prop.setterSelector().removeSuffix(":"))
                builder.appendLine("/** Convenience overload — accepts Kotlin [String] for the NSString property. */")
                builder.appendLine("open fun $setterFn(value: String) = $setterFn(ObjCRuntime.newNSString(Arena.global(), value))")
                builder.appendLine()
            }
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

    /**
     * Returns true when [kotlinName] exists in a (non-NSObject) superclass of the current
     * [_className].  Walks up the [classHierarchy] until it finds the signature in
     * [classMethodSignatures] or reaches a root.
     */
    private fun isOverride(kotlinName: String): Boolean {
        var current: String? = _className
        while (current != null) {
            val name = current
            current = classHierarchy[name]
            if (current == null) return false
            val parentMethods = classMethodSignatures[current]
            if (parentMethods != null && kotlinName in parentMethods) return true
        }
        return false
    }
}
