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
import org.openjdk.kextract.impl.DeclarationImpl.Skip
import org.openjdk.kextract.impl.IncludeHelper
import org.openjdk.kextract.impl.Utils

class IncludeFilter(private val includeHelper: IncludeHelper) : Declaration.Visitor<Void?, Declaration?> {

    fun scan(header: Declaration.Scoped): Declaration.Scoped {
        header.members().forEach { it.accept(this, null) }
        return header
    }

    override fun visitConstant(constant: Declaration.Constant, parent: Declaration?): Void? {
        if (!includeHelper.isIncluded(constant)) Skip.with(constant)
        return null
    }

    override fun visitFunction(funcTree: Declaration.Function, parent: Declaration?): Void? {
        if (!includeHelper.isIncluded(funcTree)) Skip.with(funcTree)
        return null
    }

    override fun visitScoped(d: Declaration.Scoped, parent: Declaration?): Void? {
        if (Utils.isStructOrUnion(d)) {
            val name = d.name()
            // A named struct from "typedef struct { ... } Foo" has its redundant typedef filtered out,
            // so users specify --include-typedef Foo. Accept if either STRUCT or TYPEDEF set contains the name.
            if (name.isNotEmpty() && !includeHelper.isIncluded(d) && !includeHelper.isIncludedAsTypedef(name)) {
                Skip.with(d)
            }
        }
        d.members().forEach { it.accept(this, d) }
        return null
    }

    override fun visitTypedef(tree: Declaration.Typedef, parent: Declaration?): Void? {
        if (!includeHelper.isIncluded(tree)) Skip.with(tree)
        return null
    }

    override fun visitVariable(tree: Declaration.Variable, parent: Declaration?): Void? {
        if (parent == null && !includeHelper.isIncluded(tree)) Skip.with(tree)
        return null
    }

    override fun visitDeclaration(decl: Declaration, parent: Declaration?): Void? = null
}
