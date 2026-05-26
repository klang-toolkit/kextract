/*
 * Copyright (c) 2020, 2025, Oracle and/or its affiliates. All rights reserved.
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

import org.openjdk.kextract.Declaration
import org.openjdk.kextract.JavaSourceFile
import org.openjdk.kextract.Type
import org.openjdk.kextract.impl.DeclarationImpl.JavaName
import org.openjdk.kextract.impl.DeclarationImpl.NestedDeclarations
import org.openjdk.kextract.impl.DeclarationImpl.Skip
import org.openjdk.kextract.newimpl.Options

/*
 * Scan a header file and generate Java source items for entities defined in that header
 * file. Tree visitor visit methods return true/false depending on whether a
 * particular Tree is processed or skipped.
 */
/**
 * A Kotlin-native builder interface that replaces OutputFactory.Builder for the new Kotlin pipeline.
 * Uses StructBuilder instead of StructBuilder for addStruct.
 */
internal interface Builder {

    fun addVar(varTree: Declaration.Variable) {
        throw UnsupportedOperationException("Not implemented")
    }

    fun addFunction(funcTree: Declaration.Function) {
        throw UnsupportedOperationException("Not implemented")
    }

    fun addConstant(constantTree: Declaration.Constant) {
        throw UnsupportedOperationException("Not implemented")
    }

    fun addTypedef(typedefTree: Declaration.Typedef, superClass: String?) {
        addTypedef(typedefTree, superClass, typedefTree.type())
    }

    fun addTypedef(typedefTree: Declaration.Typedef, superClass: String?, type: Type) {
        throw UnsupportedOperationException("Not implemented")
    }

    fun addStruct(structTree: Declaration.Scoped): StructBuilder {
        throw UnsupportedOperationException("Not implemented")
    }

    fun addFunctionalInterface(parentDecl: Declaration, funcType: Type.Function) {
        throw UnsupportedOperationException("Not implemented")
    }
}

internal class OutputFactory private constructor(
    private val toplevelBuilder: ToplevelBuilder
) : Declaration.Visitor<Void?, Declaration?> {

    private var currentBuilder: Builder = toplevelBuilder

    companion object {
        @JvmStatic
        fun generateWrapped(
            decl: Declaration.Scoped,
            pkgName: String,
            libs: List<Options.Library>,
            useSystemLoadLibrary: Boolean,
            sharedClassName: String?
        ): Array<JavaSourceFile> {
            val clsName = JavaName.getOrThrow(decl)
            val toplevelBuilder = ToplevelBuilder(
                pkgName, clsName, libs, useSystemLoadLibrary, sharedClassName
            )
            return OutputFactory(toplevelBuilder).generate(decl)
        }
    }

    private fun generate(decl: Declaration.Scoped): Array<JavaSourceFile> {
        // generate all decls
        decl.members().forEach { generateDecl(it) }
        val files = mutableListOf<JavaSourceFile>()
        files.addAll(toplevelBuilder.toFiles())
        return files.toTypedArray()
    }

    private fun generateDecl(tree: Declaration) {
        tree.accept(this, null)
    }

    override fun visitConstant(constant: Declaration.Constant, parent: Declaration?): Void? {
        if (Skip.isPresent(constant)) {
            return null
        }

        toplevelBuilder.addConstant(constant)
        return null
    }

    override fun visitScoped(d: Declaration.Scoped, parent: Declaration?): Void? {
        if (Skip.isPresent(d)) {
            return null
        }

        Skip.with(d) // do not generate twice
        val isStructKind = Utils.isStructOrUnion(d)
        var prevBuilder: Builder? = null
        var structBuilder: StructBuilder? = null
        if (isStructKind) {
            prevBuilder = currentBuilder
            structBuilder = currentBuilder.addStruct(d)
            currentBuilder = structBuilder
        }
        try {
            d.members().forEach { fieldTree -> fieldTree.accept(this, d) }
        } finally {
            if (isStructKind) {
                structBuilder!!.end()
                currentBuilder = prevBuilder!!
            }
        }
        return null
    }

    private fun generateFunctionalInterface(parentDecl: Declaration, func: Type.Function) {
        currentBuilder.addFunctionalInterface(parentDecl, func)
    }

    override fun visitFunction(funcTree: Declaration.Function, parent: Declaration?): Void? {
        if (Skip.isPresent(funcTree)) {
            return null
        }

        // check for function pointer type arguments
        for (param in funcTree.parameters()) {
            Utils.forEachNested(param) { s -> s.accept(this, param) }
            val f = Utils.getAsFunctionPointer(param.type())
            if (f != null) {
                generateFunctionalInterface(param, f)
            }
        }

        Utils.forEachNested(funcTree) { s -> s.accept(this, funcTree) }

        // return type could be a function pointer type
        val returnFunc = Utils.getAsFunctionPointer(funcTree.type().returnType())
        if (returnFunc != null) {
            generateFunctionalInterface(funcTree, returnFunc)
        }

        toplevelBuilder.addFunction(funcTree)
        return null
    }

    override fun visitTypedef(tree: Declaration.Typedef, parent: Declaration?): Void? {
        if (Skip.isPresent(tree)) {
            return null
        }
        val type = tree.type()
        Utils.forEachNested(tree) { s -> s.accept(this, null) }

        val structOrUnionDecl = Utils.structOrUnionDecl(type)
        if (structOrUnionDecl != null) {
            if (!structOrUnionDecl.name().isEmpty() ||
                !NestedDeclarations.get(tree).orElse(listOf()).contains(structOrUnionDecl)
            ) {
                // Only generate a typedef class if (a) struct/union name is non-empty,
                // or if (b) the declaration of the struct/union is not nested inside this typedef,
                // which indicates a typedef of some other typedef.
                toplevelBuilder.addTypedef(tree, JavaName.getFullNameOrThrow(structOrUnionDecl))
            }
        } else if (type is Type.Primitive) {
            toplevelBuilder.addTypedef(tree, null)
        } else {
            val func = Utils.getAsFunctionPointer(type)
            if (func != null) {
                generateFunctionalInterface(tree, func)
            } else if ((type as TypeImpl).isPointer()) {
                toplevelBuilder.addTypedef(tree, null)
            } else {
                val primitive = Utils.getAsSignedOrUnsigned(type)
                if (primitive != null) {
                    toplevelBuilder.addTypedef(tree, null, primitive)
                }
            }
        }
        return null
    }

    override fun visitVariable(tree: Declaration.Variable, parent: Declaration?): Void? {
        if (Skip.isPresent(tree)) {
            return null
        }
        val type = tree.type()

        Utils.forEachNested(tree) { s -> s.accept(this, tree) }

        val fieldName = tree.name()
        assert(fieldName.isNotEmpty())

        val func = Utils.getAsFunctionPointer(type)
        if (func != null) {
            generateFunctionalInterface(tree, func)
        }

        currentBuilder.addVar(tree)
        return null
    }
}
