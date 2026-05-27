/*
 * Copyright (c) 2024 Oracle and/or its affiliates. All rights reserved.
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
import org.openjdk.kextract.Position
import org.openjdk.kextract.Type
import org.openjdk.kextract.Type.Delegated
import org.openjdk.kextract.impl.DeclarationImpl.Skip
import org.openjdk.kextract.impl.Utils

class MissingDepChecker(private val logger: Logger) : Declaration.Visitor<Void?> {

    private var currentParent: Declaration? = null

    fun scan(header: Declaration.Scoped): Declaration.Scoped {
        header.members().forEach { it.accept(this) }
        return header
    }

    override fun visitFunction(funcTree: Declaration.Function): Void? {
        if (Skip.isPresent(funcTree)) return null

        val posDecl = currentParent ?: funcTree
        val saved = currentParent
        currentParent = posDecl
        funcTree.parameters().forEach { it.accept(this) }
        Utils.forEachNested(funcTree) { it.accept(this) }
        currentParent = saved
        checkMissingDep(posDecl, funcTree.type())
        return null
    }

    override fun visitScoped(d: Declaration.Scoped): Void? {
        if (Skip.isPresent(d)) return null

        val posDecl = currentParent ?: d
        val saved = currentParent
        currentParent = posDecl
        d.members().forEach { it.accept(this) }
        currentParent = saved
        return null
    }

    override fun visitTypedef(tree: Declaration.Typedef): Void? {
        if (Skip.isPresent(tree)) return null

        val posDecl = currentParent ?: tree
        val saved = currentParent
        currentParent = posDecl
        Utils.forEachNested(tree) { it.accept(this) }
        currentParent = saved
        checkMissingDep(posDecl, tree.type())
        Utils.getAsFunctionPointer(tree.type())?.let { checkMissingDep(posDecl, it) }
        return null
    }

    override fun visitVariable(tree: Declaration.Variable): Void? {
        if (Skip.isPresent(tree)) return null

        val posDecl = currentParent ?: tree
        val saved = currentParent
        currentParent = posDecl
        Utils.forEachNested(tree) { it.accept(this) }
        currentParent = saved
        checkMissingDep(posDecl, tree.type())
        Utils.getAsFunctionPointer(tree.type())?.let { checkMissingDep(posDecl, it) }
        return null
    }

    // ObjC: all types reduce to MemorySegment — no missing dep checks needed
    override fun visitObjCClass(d: Declaration.ObjCClass): Void? = null
    override fun visitObjCProtocol(d: Declaration.ObjCProtocol): Void? = null
    override fun visitObjCCategory(d: Declaration.ObjCCategory): Void? = null

    override fun visitDeclaration(decl: Declaration): Void? = null

    private fun checkMissingDep(decl: Declaration, function: Type.Function) {
        checkMissingDep(decl, function.returnType())
        function.argumentTypes().forEach { checkMissingDep(decl, it) }
    }

    private fun checkMissingDep(decl: Declaration, type: Type) {
        when {
            type is Type.Declared -> {
                if (Skip.isPresent(type.tree())) {
                    logger.err("kextract.bad.include", decl.name(), type.tree().name(),
                        pos = decl.pos())
                }
            }
            type is Delegated && type.kind() == Delegated.Kind.TYPEDEF ->
                checkMissingDep(decl, type.type())
            type is Type.Array ->
                checkMissingDep(decl, type.elementType())
        }
    }

}
