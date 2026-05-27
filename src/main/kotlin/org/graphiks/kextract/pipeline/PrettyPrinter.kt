package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Position
import org.graphiks.kextract.Type
import org.graphiks.kextract.TypeImpl

class PrettyPrinter : Declaration.Visitor<Unit> {

    companion object {
        private val SPACES = " ".repeat(92)

        private val typeVisitor = object : Type.Visitor<String> {
            override fun visitPrimitive(t: Type.Primitive): String =
                t.kind().toString()

            override fun visitDelegated(t: Type.Delegated): String =
                when (t.kind()) {
                    Type.Delegated.Kind.TYPEDEF ->
                        "typedef ${t.name() ?: ""} = ${t.type().accept(this)}"
                    Type.Delegated.Kind.POINTER ->
                        "(${t.type().accept(this)})*"
                    else ->
                        "${t.kind()} = ${t.type().accept(this)}"
                }

            override fun visitFunction(t: Type.Function): String {
                val res = t.returnType().accept(this)
                val args = t.argumentTypes().joinToString(",", "(", ")") { it.accept(this) }
                return "$res$args"
            }

            override fun visitDeclared(t: Type.Declared): String =
                "Declared(${t.tree().name()})"

            override fun visitArray(t: Type.Array): String {
                val prefix = if (t.kind() == Type.Array.Kind.VECTOR) "v" else ""
                val count = t.elementCount()?.toString() ?: ""
                return "${t.elementType().accept(this)}$prefix[$count]"
            }

            override fun visitType(t: Type): String =
                if (t.isErroneous())
                    "<error: ${(t as TypeImpl.ErronrousTypeImpl).erroneousName}>"
                else
                    "<unknown: ${t.javaClass.name}>"
        }

        fun type(type: Type): String = type.accept(typeVisitor)

        fun position(pos: Position): String =
            "${pos.path ?: "N/A"}:${pos.line}:${pos.col}"
    }

    private var align = 0
    private val builder = StringBuilder()

    private fun incr() { align += 4 }
    private fun decr() { align -= 4 }
    private fun indent() { builder.append(SPACES.substring(0, align)) }

    private fun getAttributes(decl: Declaration) {
        val attrs = decl.attributes()
        if (attrs.isEmpty()) return
        incr()
        indent()
        builder.append("Attributes: ")
        var sep = "\n"
        for (attr in attrs) {
            builder.append(sep)
            incr()
            indent()
            builder.append(attr)
            decr()
            sep = ",\n"
        }
        builder.append("\n")
        decr()
    }

    fun print(decl: Declaration): String {
        decl.accept(this)
        return builder.toString()
    }

    override fun visitScoped(d: Declaration.Scoped) {
        indent()
        builder.append("Scoped: ${d.kind()} ${d.name()}\n")
        getAttributes(d)
        incr()
        d.members().forEach { it.accept(this) }
        decr()
    }

    override fun visitFunction(d: Declaration.Function) {
        indent()
        builder.append("Function: ${d.name()} type = ${d.type().accept(typeVisitor)}\n")
        getAttributes(d)
        incr()
        d.parameters().forEach { it.accept(this) }
        decr()
    }

    override fun visitVariable(d: Declaration.Variable) {
        indent()
        if (d is Declaration.Bitfield) {
            builder.append("Bitfield:  type = ${d.type().accept(typeVisitor)}, name = ${d.name()}, width = ${d.width()}")
        } else {
            builder.append("Variable: ${d.kind()} ${d.name()} type = ${d.type().accept(typeVisitor)}")
        }
        builder.append("\n")
        getAttributes(d)
    }

    override fun visitConstant(d: Declaration.Constant) {
        indent()
        builder.append("Constant: ${d.name()} ${d.value()} type = ${d.type().accept(typeVisitor)}\n")
        getAttributes(d)
    }

    override fun visitTypedef(d: Declaration.Typedef) {
        indent()
        builder.append("Typedef: ${d.name()} = ${d.type().accept(typeVisitor)}\n")
        getAttributes(d)
    }
}
