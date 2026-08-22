// src/main/kotlin/org/openjdk/kextract/kotlin/builders/KotlinTypedefBuilder.kt
package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type

/**
 * Generates Kotlin code for typedefs.
 *
 * Note: NS_ENUM / NS_OPTIONS enums (typedef enum : long { … } Foo) are NOT handled here because
 * clang names the inner enum with the same identifier as the typedef, making the typedef
 * "redundant" and causing it to be filtered before [visitTypedef] is called.  Those enums are
 * instead handled by [KotlinToplevelBuilder.visitScoped] when it encounters
 * [Declaration.Scoped.Kind.ENUM] with a non-empty name.
 *
 * Everything else (function-pointer typedefs, primitive typedefs, struct/union aliases) produces
 * a plain `typealias` here.
 */
class KotlinTypedefBuilder(private val builder: SourceBuilder, private val toplevel: KotlinToplevelBuilder) {

    fun visitTypedef(decl: Declaration.Typedef) {
        // Fallback: plain typealias
        val name = toplevel.javaName(decl.name())
        val type = toplevel.mapType(decl.type())

        // Skip self-referencing typealiases (e.g. `typedef unsigned char Byte` → typealias Byte = Byte)
        if (name == type) return

        // KDoc
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : typedef ${decl.type()} ${decl.name()};}")
        builder.appendLine(" */")

        // Type alias
        builder.appendLine("typealias ${name} = ${type}")
        builder.appendLine()
    }
}
