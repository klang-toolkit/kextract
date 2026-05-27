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

class IncludeFilter(private val includeHelper: IncludeHelper) : Declaration.Visitor<Unit> {

    private var currentParent: Declaration? = null

    fun scan(header: Declaration.Scoped): Declaration.Scoped {
        header.members().forEach { it.accept(this) }
        return header
    }

    override fun visitConstant(constant: Declaration.Constant) {
        if (!includeHelper.isIncluded(constant)) Skip.with(constant)
    }

    override fun visitFunction(funcTree: Declaration.Function) {
        if (!includeHelper.isIncluded(funcTree)) Skip.with(funcTree)
    }

    override fun visitScoped(d: Declaration.Scoped) {
        if (Utils.isStructOrUnion(d)) {
            val name = d.name()
            // A named struct from "typedef struct { ... } Foo" has its redundant typedef filtered out,
            // so users specify --include-typedef Foo. Accept if either STRUCT or TYPEDEF set contains the name.
            if (name.isNotEmpty() && !includeHelper.isIncluded(d) && !includeHelper.isIncludedAsTypedef(name)) {
                Skip.with(d)
            }
        }
        val saved = currentParent
        currentParent = d
        d.members().forEach { it.accept(this) }
        currentParent = saved
    }

    override fun visitTypedef(tree: Declaration.Typedef) {
        if (!includeHelper.isIncluded(tree)) Skip.with(tree)
    }

    override fun visitVariable(tree: Declaration.Variable) {
        if (currentParent == null && !includeHelper.isIncluded(tree)) Skip.with(tree)
    }

    override fun visitObjCClass(d: Declaration.ObjCClass) {
        if (!includeHelper.isIncluded(d)) Skip.with(d)
    }

    override fun visitObjCProtocol(d: Declaration.ObjCProtocol) {
        if (!includeHelper.isIncluded(d)) Skip.with(d)
    }

    override fun visitObjCCategory(d: Declaration.ObjCCategory) {
        if (!includeHelper.isIncluded(d)) Skip.with(d)
    }
}
