/*
 *  Copyright (c) 2020, 2024, Oracle and/or its affiliates. All rights reserved.
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 *  This code is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License version 2 only, as
 *  published by the Free Software Foundation.  Oracle designates this
 *  particular file as subject to the "Classpath" exception as provided
 *  by Oracle in the LICENSE file that accompanied this code.
 *
 *  This code is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 *  FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 *  version 2 for more details (a copy is included in the LICENSE file that
 *  accompanied this code).
 *
 *  You should have received a copy of the GNU General Public License version
 *  2 along with this work; if not, write to the Free Software Foundation,
 *  Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 *   Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 *  or visit www.oracle.com if you need additional information or have any
 *  questions.
 *
 */
package org.openjdk.kextract.impl

import org.openjdk.kextract.Declaration
import org.openjdk.kextract.Position
import org.openjdk.kextract.Type

class PrettyPrinter : Declaration.Visitor<Void?, Void?> {

    companion object {
        private val SPACES = " ".repeat(92)

        private val typeVisitor = object : Type.Visitor<String, Void?> {
            override fun visitPrimitive(t: Type.Primitive, aVoid: Void?): String =
                t.kind().toString()

            override fun visitDelegated(t: Type.Delegated, aVoid: Void?): String =
                when (t.kind()) {
                    Type.Delegated.Kind.TYPEDEF ->
                        "typedef ${t.name().orElse("")} = ${t.type().accept(this, null)}"
                    Type.Delegated.Kind.POINTER ->
                        "(${t.type().accept(this, null)})*"
                    else ->
                        "${t.kind()} = ${t.type().accept(this, null)}"
                }

            override fun visitFunction(t: Type.Function, aVoid: Void?): String {
                val res = t.returnType().accept(this, null)
                val args = t.argumentTypes().joinToString(",", "(", ")") { it.accept(this, null) }
                return "$res$args"
            }

            override fun visitDeclared(t: Type.Declared, aVoid: Void?): String =
                "Declared(${t.tree().name()})"

            override fun visitArray(t: Type.Array, aVoid: Void?): String {
                val prefix = if (t.kind() == Type.Array.Kind.VECTOR) "v" else ""
                val count = if (t.elementCount().isPresent) t.elementCount().asLong.toString() else ""
                return "${t.elementType().accept(this, null)}$prefix[$count]"
            }

            override fun visitType(t: Type, aVoid: Void?): String =
                if (t.isErroneous())
                    "<error: ${(t as TypeImpl.ErronrousTypeImpl).erroneousName}>"
                else
                    "<unknown: ${t.javaClass.name}>"
        }

        @JvmStatic
        fun type(type: Type): String = type.accept(typeVisitor, null)

        @JvmStatic
        fun position(pos: Position): String =
            "${if (pos.path() == null) "N/A" else pos.path().toString()}:${pos.line()}:${pos.col()}"
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
        decl.accept(this, null)
        return builder.toString()
    }

    override fun visitScoped(d: Declaration.Scoped, aVoid: Void?): Void? {
        indent()
        builder.append("Scoped: ${d.kind()} ${d.name()}\n")
        getAttributes(d)
        incr()
        d.members().forEach { it.accept(this, null) }
        decr()
        return null
    }

    override fun visitFunction(d: Declaration.Function, aVoid: Void?): Void? {
        indent()
        builder.append("Function: ${d.name()} type = ${d.type().accept(typeVisitor, null)}\n")
        getAttributes(d)
        incr()
        d.parameters().forEach { it.accept(this, null) }
        decr()
        return null
    }

    override fun visitVariable(d: Declaration.Variable, aVoid: Void?): Void? {
        indent()
        if (d is Declaration.Bitfield) {
            builder.append("Bitfield:  type = ${d.type().accept(typeVisitor, null)}, name = ${d.name()}, width = ${d.width()}")
        } else {
            builder.append("Variable: ${d.kind()} ${d.name()} type = ${d.type().accept(typeVisitor, null)}")
        }
        builder.append("\n")
        getAttributes(d)
        return null
    }

    override fun visitConstant(d: Declaration.Constant, aVoid: Void?): Void? {
        indent()
        builder.append("Constant: ${d.name()} ${d.value()} type = ${d.type().accept(typeVisitor, null)}\n")
        getAttributes(d)
        return null
    }

    override fun visitTypedef(d: Declaration.Typedef, aVoid: Void?): Void? {
        indent()
        builder.append("Typedef: ${d.name()} = ${d.type().accept(typeVisitor, null)}\n")
        getAttributes(d)
        return null
    }
}
