/*
 * Copyright (c) 2022, 2024 Oracle and/or its affiliates. All rights reserved.
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
import org.openjdk.kextract.DeclarationImpl.Skip

class DuplicateFilter : Declaration.Visitor<Unit> {

    private val constants = mutableSetOf<String>()
    private val variables = mutableSetOf<String>()
    private val typedefs = mutableSetOf<String>()
    private val functions = mutableSetOf<String>()
    private val objcClasses = mutableSetOf<String>()
    private val objcProtocols = mutableSetOf<String>()
    private val objcCategories = mutableSetOf<String>()

    fun scan(header: Declaration.Scoped): Declaration.Scoped {
        header.members().forEach { it.accept(this) }
        return header
    }

    override fun visitConstant(constant: Declaration.Constant) {
        if (!constants.add(constant.name())) Skip.with(constant)
    }

    override fun visitFunction(funcTree: Declaration.Function) {
        if (!functions.add(funcTree.name())) Skip.with(funcTree)
    }

    override fun visitTypedef(tree: Declaration.Typedef) {
        if (!typedefs.add(tree.name())) Skip.with(tree)
    }

    override fun visitVariable(tree: Declaration.Variable) {
        if (!variables.add(tree.name())) Skip.with(tree)
    }

    override fun visitScoped(d: Declaration.Scoped) {
        if (d.isEnum()) {
            d.members().forEach { it.accept(this) }
        }
    }

    override fun visitObjCClass(d: Declaration.ObjCClass) {
        if (!objcClasses.add(d.name())) Skip.with(d)
    }

    override fun visitObjCProtocol(d: Declaration.ObjCProtocol) {
        if (!objcProtocols.add(d.name())) Skip.with(d)
    }

    override fun visitObjCCategory(d: Declaration.ObjCCategory) {
        // key = "ClassName(CategoryName)" to allow multiple categories on same class
        val key = "${d.extendedClass()}(${d.categoryName()})"
        if (!objcCategories.add(key)) Skip.with(d)
    }
}
