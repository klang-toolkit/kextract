// src/main/kotlin/org/openjdk/kextract/kotlin/builders/KotlinFunctionalBuilder.kt
package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.kotlin.utils.TypeMapper

/**
 * Generates Kotlin code for function pointers (functional interfaces).
 */
class KotlinFunctionalBuilder(private val builder: SourceBuilder, private val toplevel: KotlinToplevelBuilder) {

    fun visitFunctionalInterface(decl: Declaration.Function) {
        val name = toplevel.javaName(decl.name())
        val returnType = TypeMapper.map(decl.type())
        val paramTypes = decl.type().argumentTypes().joinToString(", ") {
            TypeMapper.map(it)
        }

        // KDoc
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : ${decl.name()} ${decl.type()}")
        builder.appendLine(" */")

        // Functional interface
        builder.appendLine("fun interface ${name} {")
        builder.indent()
        builder.appendLine("fun apply(${paramTypes}): ${returnType}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()

        // Descriptor and allocation
        builder.appendLine("val ${name} \$DESC: FunctionDescriptor = ${functionDescriptorString(decl)}")
        builder.appendLine()
        builder.appendLine("fun ${name} \$allocate(fi: ${name}, arena: Arena): MemorySegment =")
        builder.appendLine("    Linker.nativeLinker().upcallStub(")
        builder.indent()
        builder.appendLine("MethodHandles.lookup().findVirtual(${name}::class.java, \"apply\", ${name} \$DESC.toMethodType()),")
        builder.appendLine("${name} \$DESC,")
        builder.appendLine("arena")
        builder.unindent()
        builder.appendLine(")")
        builder.appendLine()
    }

    private fun functionDescriptorString(decl: Declaration.Function): String {
        val returnType = TypeMapper.map(decl.type())
        val argTypes = decl.type().argumentTypes().joinToString(", ") {
            TypeMapper.map(it)
        }
        return "FunctionDescriptor.of(${returnType}, ${argTypes})"
    }
}
