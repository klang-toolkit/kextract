/*
 * Copyright (c) 2023, 2024 Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.openjdk.kextract.impl

import org.openjdk.kextract.newimpl.Logger
import org.openjdk.kextract.Declaration
import org.openjdk.kextract.Declaration.Constant
import org.openjdk.kextract.Declaration.Function
import org.openjdk.kextract.Declaration.Scoped
import org.openjdk.kextract.Declaration.Scoped.Kind
import org.openjdk.kextract.Declaration.Typedef
import org.openjdk.kextract.Declaration.Variable
import org.openjdk.kextract.Position
import org.openjdk.kextract.Type
import org.openjdk.kextract.Type.Declared
import org.openjdk.kextract.impl.DeclarationImpl.AnonymousStruct
import org.openjdk.kextract.impl.DeclarationImpl.ClangSizeOf
import org.openjdk.kextract.impl.DeclarationImpl.Skip
import org.openjdk.kextract.impl.TypeImpl
import org.openjdk.kextract.impl.Utils

class UnsupportedFilter(private val logger: Logger) : Declaration.Visitor<Void?, Declaration?> {

    fun scan(header: Scoped): Scoped {
        header.members().forEach { it.accept(this, null) }
        return header
    }

    override fun visitFunction(funcTree: Function, firstNamedParent: Declaration?): Void? {
        if (Skip.isPresent(funcTree)) return null
        Utils.forEachNested(funcTree) { it.accept(this, firstNamedParent) }

        val unsupportedType = firstUnsupportedType(funcTree.type(), false)
        if (unsupportedType != null) {
            warnSkip(funcTree.pos(), funcTree.name(), unsupportedType(unsupportedType))
            Skip.with(funcTree)
            return null
        }

        for (param in funcTree.parameters()) {
            Utils.forEachNested(param) { it.accept(this, firstNamedParent) }
            val f = Utils.getAsFunctionPointer(param.type())
            if (f != null && !checkFunctionTypeSupported(param, f, funcTree.name())) {
                Skip.with(funcTree)
                return null
            }
        }

        val returnFunc = Utils.getAsFunctionPointer(funcTree.type().returnType())
        if (returnFunc != null && !checkFunctionTypeSupported(funcTree, returnFunc, funcTree.name())) {
            Skip.with(funcTree)
        }
        return null
    }

    override fun visitVariable(varTree: Variable, firstNamedParent: Declaration?): Void? {
        if (Skip.isPresent(varTree)) return null
        Utils.forEachNested(varTree) { it.accept(this, varTree) }

        val name = fieldName(firstNamedParent, varTree)
        val unsupportedType = firstUnsupportedType(varTree.type(), false)
        if (unsupportedType != null) {
            warnSkip(varTree.pos(), name, unsupportedType(unsupportedType))
            Skip.with(varTree)
            return null
        }

        val func = Utils.getAsFunctionPointer(varTree.type())
        if (func != null && !checkFunctionTypeSupported(varTree, func, name)) {
            Skip.with(varTree)
        }
        return null
    }

    override fun visitScoped(scoped: Scoped, firstNamedParent: Declaration?): Void? {
        if (Skip.isPresent(scoped)) return null

        val unsupportedType = firstUnsupportedType(Type.declared(scoped), false)
        if (unsupportedType != null) {
            warnSkip(scoped.pos(), scoped.name(), unsupportedType(unsupportedType))
            Skip.with(scoped)
            return null
        }

        if (scoped.kind() == Kind.BITFIELDS) {
            for (bitField in scoped.members()) {
                if (bitField.name().isNotEmpty()) {
                    warnSkip(scoped.pos(), fieldName(firstNamedParent, bitField), unsupportedBitfield())
                }
            }
            Skip.with(scoped)
            return null
        }

        val newNamedParent = if (scoped.name().isNotEmpty()) scoped else firstNamedParent
        scoped.members().forEach { it.accept(this, newNamedParent) }
        return null
    }

    override fun visitTypedef(typedefTree: Typedef, firstNamedParent: Declaration?): Void? {
        if (Skip.isPresent(typedefTree)) return null

        if (typedefTree.type() is Declared) {
            visitScoped((typedefTree.type() as Declared).tree(), null)
        }

        val unsupportedType = firstUnsupportedType(typedefTree.type(), false)
        if (unsupportedType != null) {
            warnSkip(typedefTree.pos(), typedefTree.name(), unsupportedType(unsupportedType))
            Skip.with(typedefTree)
            return null
        }

        val func = Utils.getAsFunctionPointer(typedefTree.type())
        if (func != null && !checkFunctionTypeSupported(typedefTree, func, typedefTree.name())) {
            Skip.with(typedefTree)
        }
        return null
    }

    override fun visitConstant(d: Constant, firstNamedParent: Declaration?): Void? {
        if (Skip.isPresent(d)) return null

        val name = fieldName(firstNamedParent, d)
        val unsupportedType = firstUnsupportedType(d.type(), false)
        if (unsupportedType != null) {
            warnSkip(d.pos(), name, unsupportedType(unsupportedType))
            Skip.with(d)
        }
        return null
    }

    override fun visitDeclaration(d: Declaration, firstNamedParent: Declaration?): Void? = null

    private fun checkFunctionTypeSupported(decl: Declaration, func: Type.Function, nameOfSkipped: String): Boolean {
        val unsupportedType = firstUnsupportedType(func, false)
        if (unsupportedType != null) {
            warnSkip(decl.pos(), nameOfSkipped, unsupportedType(unsupportedType))
            return false
        }
        if (func.varargs() && func.argumentTypes().isNotEmpty()) {
            warnSkip(decl.pos(), nameOfSkipped, unsupportedVariadicCallback(decl.name()))
            return false
        }
        return true
    }

    private fun warnSkip(pos: Position, treeName: String, message: String) {
        logger.warn("kextract.skip.unsupported", treeName, message, pos = pos)
    }

    private fun unsupportedType(type: Type): String = logger.format("unsupported.type", type)

    private fun unsupportedVariadicCallback(name: String): String =
        logger.format("unsupported.variadic.callback", name)

    private fun unsupportedBitfield(): String = logger.format("unsupported.bitfields")

    companion object {
        private fun fieldName(firstNamedParent: Declaration?, decl: Declaration): String {
            val prefix = if (firstNamedParent != null) "${firstNamedParent.name()}." else ""
            return prefix + decl.name()
        }

        fun firstUnsupportedType(type: Type, allowVoid: Boolean): Type? =
            type.accept(UNSUPPORTED_VISITOR, allowVoid)

        private val UNSUPPORTED_VISITOR = object : Type.Visitor<Type?, Boolean> {
            override fun visitPrimitive(t: Type.Primitive, allowVoid: Boolean): Type? = when (t.kind()) {
                Type.Primitive.Kind.Char16,
                Type.Primitive.Kind.Float128,
                Type.Primitive.Kind.HalfFloat,
                Type.Primitive.Kind.Int128,
                Type.Primitive.Kind.WChar -> t
                Type.Primitive.Kind.LongDouble -> if (TypeImpl.IS_WINDOWS) null else t
                Type.Primitive.Kind.Void -> if (allowVoid) null else t
                else -> null
            }

            override fun visitFunction(t: Type.Function, allowVoid: Boolean): Type? {
                for (arg in t.argumentTypes()) {
                    firstUnsupportedType(arg, false)?.let { return it }
                }
                return firstUnsupportedType(t.returnType(), true)
            }

            override fun visitDeclared(t: Declared, allowVoid: Boolean): Type? {
                if (t.tree().kind() == Kind.STRUCT || t.tree().kind() == Kind.UNION) {
                    if (!isValidStructOrUnion(t.tree())) return t
                }
                return null
            }

            private fun isValidStructOrUnion(scoped: Scoped): Boolean {
                if (ClangSizeOf.get(scoped).isEmpty) return false
                if (AnonymousStruct.isPresent(scoped) &&
                    AnonymousStruct.getOrThrow(scoped).offset.isEmpty) return false
                return true
            }

            override fun visitDelegated(t: Type.Delegated, allowVoid: Boolean): Type? =
                if (t.kind() != Type.Delegated.Kind.POINTER)
                    firstUnsupportedType(t.type(), allowVoid)
                else null

            override fun visitArray(t: Type.Array, allowVoid: Boolean): Type? =
                firstUnsupportedType(t.elementType(), false)

            override fun visitType(t: Type, allowVoid: Boolean): Type? =
                if (t.isErroneous()) t else null
        }

    }
}
