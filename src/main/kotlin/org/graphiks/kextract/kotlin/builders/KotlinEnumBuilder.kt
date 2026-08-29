// src/main/kotlin/org/openjdk/kextract/kotlin/builders/KotlinEnumBuilder.kt
package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration

/**
 * Generates Kotlin code for named C/ObjC enumerations.
 *
 * Three output styles are supported:
 * - **C enum style** — closed `enum class Foo(val value: Long)`.
 * - **Objective-C NS_ENUM style** — open `value class Foo(val rawValue: Long)`.
 * - **NS_OPTIONS style** — `@JvmInline value class Foo(val rawValue: Long)` with bit operators.
 *   Triggered by Clang's `FlagEnum` semantic attribute.
 *
 * This builder is invoked from [KotlinToplevelBuilder] when it encounters a
 * [Declaration.Scoped] of kind [Declaration.Scoped.Kind.ENUM] with a non-empty name.
 * For ObjC fixed-underlying-type enums (`typedef enum : long { … } Foo`) clang creates a
 * *named* ENUM scoped (the typedef is redundant and filtered), so this is the correct hook.
 */
class KotlinEnumBuilder(
    private val builder: SourceBuilder,
    private val toplevel: KotlinToplevelBuilder,
    private val externalConstants: List<Declaration.Constant> = emptyList(),
) {
    private data class EnumEntry(
        val name: String,
        val value: Long,
        val declaration: Declaration.Constant,
    )

    private fun regularEntries(constants: List<Declaration.Constant>): List<EnumEntry> {
        val entries = constants.map {
            EnumEntry(toplevel.javaName(it.name()), it.value().toLongValue(), it)
        }.toMutableList()
        val seenValues = entries.mapTo(mutableSetOf()) { it.value }
        val seenNames = entries.mapTo(mutableSetOf()) { it.name }
        for (constant in externalConstants) {
            val value = constant.value().toLongValue()
            if (!seenValues.add(value)) continue

            val baseName = toplevel.javaName(constant.name())
            var entryName = baseName
            var suffix = 1
            while (!seenNames.add(entryName)) {
                entryName = "${baseName}_kextract${suffix++}"
            }
            entries += EnumEntry(entryName, value, constant)
        }
        return entries
    }

    fun visitEnum(decl: Declaration.Scoped) {
        require(decl.kind() == Declaration.Scoped.Kind.ENUM)
        require(decl.name().isNotEmpty())

        val constants = decl.members().filterIsInstance<Declaration.Constant>()
        if (KotlinEnumSupport.isOptionsStyle(decl)) {
            emitValueClass(decl, constants, options = true)
        } else if (toplevel.isObjCSurfaceEnum(decl)) {
            emitValueClass(decl, constants, options = false)
        } else {
            emitEnumClass(decl, constants)
        }
    }

    // ── NS_ENUM ───────────────────────────────────────────────────────────────

    private fun emitEnumClass(decl: Declaration.Scoped, constants: List<Declaration.Constant>) {
        val name = toplevel.javaName(decl.name())

        builder.appendLine("/**")
        builder.appendLine(" * NS_ENUM: {@snippet lang=c : enum ${decl.name()}}")
        builder.appendLine(" */")

        val entries = regularEntries(constants)
        toplevel.emitPlatformAvailability(builder, decl)
        if (entries.isEmpty()) {
            builder.appendLine("enum class ${name}(val value: Long)")
            builder.appendLine()
            return
        }

        builder.appendLine("enum class ${name}(val value: Long) {")
        builder.indent()
        entries.forEachIndexed { index, entry ->
            toplevel.emitPlatformAvailability(builder, entry.declaration)
            val terminator = if (index == entries.lastIndex) ";" else ","
            builder.appendLine("${entry.name}(${entry.value.toKotlinLongLiteral()})$terminator")
        }
        builder.appendLine()
        builder.appendLine("companion object {")
        builder.indent()
        // Use firstOrNull so unknown values (e.g. future SDK additions) produce a clear error
        // instead of an unhelpful NoSuchElementException.
        builder.appendLine("fun fromValue(v: Long): ${name} = entries.firstOrNull { it.value == v }")
        builder.appendLine("    ?: error(\"Unknown ${name} value: \$v\")")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    // ── NS_OPTIONS ────────────────────────────────────────────────────────────

    private fun emitValueClass(
        decl: Declaration.Scoped,
        constants: List<Declaration.Constant>,
        options: Boolean,
    ) {
        val name = toplevel.javaName(decl.name())

        builder.appendLine("/**")
        val objcKind = if (options) "NS_OPTIONS" else "NS_ENUM"
        builder.appendLine(" * $objcKind: {@snippet lang=c : enum ${decl.name()}}")
        builder.appendLine(" */")

        toplevel.emitPlatformAvailability(builder, decl)
        builder.appendLine("@JvmInline")
        builder.appendLine("value class ${name}(val rawValue: Long) {")
        builder.indent()

        val entries = regularEntries(constants)
        if (entries.isNotEmpty()) {
            builder.appendLine("companion object {")
            builder.indent()
            for (entry in entries) {
                toplevel.emitPlatformAvailability(builder, entry.declaration)
                builder.appendLine("val ${entry.name} = ${name}(${entry.value.toKotlinLongLiteral()})")
            }
            builder.unindent()
            builder.appendLine("}")
            builder.appendLine()
        }

        if (options) {
            builder.appendLine("operator fun plus(o: ${name}) = ${name}(rawValue or o.rawValue)")
            builder.appendLine("operator fun contains(o: ${name}) = (rawValue and o.rawValue) != 0L")
        }

        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    /** Coerce an enum constant value (Any, typically Long or Int) to a Long for Kotlin literals. */
    private fun Any.toLongValue(): Long = when (this) {
        is Long -> this
        is Int  -> this.toLong()
        else    -> toString().toLongOrNull() ?: 0L
    }

    /**
     * Renders a [Long] as a valid Kotlin literal.
     *
     * [Long.MIN_VALUE] (-9223372036854775808) cannot be written as `-9223372036854775808L` in
     * Kotlin source because the compiler parses the magnitude first and overflows before applying
     * the unary minus. Emit `Long.MIN_VALUE` for that special case.
     */
    private fun Long.toKotlinLongLiteral(): String =
        if (this == Long.MIN_VALUE) "Long.MIN_VALUE" else "${this}L"
}
