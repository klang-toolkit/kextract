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
import org.openjdk.kextract.clang.CursorKind
import org.openjdk.kextract.clang.Diagnostic
import org.openjdk.kextract.clang.LibClang
import org.openjdk.kextract.clang.SourceLocation

class Parser(private val logger: Logger) {

    private val treeMaker: TreeMaker = TreeMaker()

    private fun collectDeclarations(
        tu: org.openjdk.kextract.clang.TranslationUnit,
        macroParser: MacroParserImpl
    ): Declaration.Scoped {
        val decls = mutableListOf<Declaration>()
        val tuCursor = tu.getCursor()
        tuCursor.forEach { c ->
            val loc = c.getSourceLocation() ?: return@forEach

            val src: SourceLocation.Location = loc.getFileLocation() ?: return@forEach

            if (c.isDeclaration()) {
                if (c.kind() == CursorKind.UnexposedDecl || c.kind() == CursorKind.Namespace) {
                    c.forEach { t ->
                        val declaration = treeMaker.createTree(t)
                        if (declaration != null) {
                            decls.add(declaration)
                        }
                    }
                } else {
                    val decl = treeMaker.createTree(c)
                    if (decl != null) {
                        decls.add(decl)
                    }
                }
            } else if (isMacro(c) && src.path != null) {
                val range = c.getExtent() ?: return@forEach
                val tokens = c.getTranslationUnit().tokens(range)
                val constant = macroParser.parseConstant(c, c.spelling(), tokens)
                if (constant != null) {
                    decls.add(constant)
                }
            }
        }

        decls.addAll(macroParser.macroTable.reparseConstants())
        return treeMaker.createHeader(tuCursor, decls)
    }

    fun parse(name: String, content: String, args: Collection<String>): Declaration.Scoped {
        LibClang.createIndex(false).use { index ->
            val tu = index.parse(
                name, content,
                { d ->
                    val pos = asPosition(d.location().getSpellingLocation())
                    when {
                        d.severity() > Diagnostic.CXDiagnostic_Warning -> logger.clangErr(pos, d.spelling())
                        d.severity() == Diagnostic.CXDiagnostic_Warning -> logger.clangWarn(pos, d.spelling())
                        d.severity() == Diagnostic.CXDiagnostic_Note -> logger.clangInfo(pos, d.spelling())
                    }
                },
                true,
                *args.toTypedArray()
            )
            tu.use { translationUnit ->
                MacroParserImpl.make(treeMaker, logger, translationUnit, args).use { macroParser ->
                    return collectDeclarations(translationUnit, macroParser)
                }
            }
        }
    }

    private fun asPosition(loc: SourceLocation.Location): Position =
        if (loc.path == null) Position.NO_POSITION
        else Position(loc.path, loc.line, loc.column)

    private fun isMacro(c: org.openjdk.kextract.clang.Cursor): Boolean {
        return c.isPreprocessing() && c.kind() == CursorKind.MacroDefinition
    }
}
