package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Position
import org.graphiks.kextract.clang.CursorKind
import org.graphiks.kextract.clang.Diagnostic
import org.graphiks.kextract.clang.LibClang
import org.graphiks.kextract.clang.SourceLocation

class Parser(private val logger: Logger) {

    private val treeMaker: TreeMaker = TreeMaker()

    private fun collectDeclarations(
        tu: org.graphiks.kextract.clang.TranslationUnit,
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

    private fun isMacro(c: org.graphiks.kextract.clang.Cursor): Boolean {
        return c.isPreprocessing() && c.kind() == CursorKind.MacroDefinition
    }
}
