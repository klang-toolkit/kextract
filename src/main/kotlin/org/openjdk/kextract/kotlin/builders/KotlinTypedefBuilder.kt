// src/main/kotlin/org/openjdk/kextract/kotlin/builders/KotlinTypedefBuilder.kt
package org.openjdk.kextract.kotlin.builders

import org.openjdk.kextract.Declaration
import org.openjdk.kextract.kotlin.utils.TypeMapper

/**
 * Generates Kotlin code for typedefs.
 */
class KotlinTypedefBuilder(private val builder: SourceBuilder, private val toplevel: KotlinToplevelBuilder) {

    fun visitTypedef(decl: Declaration.Typedef) {
        val name = toplevel.javaName(decl.name())
        val type = TypeMapper.map(decl.type())

        // KDoc
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : typedef ${decl.type()} ${decl.name()};}")
        builder.appendLine(" */")

        // Type alias
        builder.appendLine("typealias ${name} = ${type}")
        builder.appendLine()
    }
}
