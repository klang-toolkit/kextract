package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Position
import org.graphiks.kextract.Type
import org.graphiks.kextract.clang.Cursor
import org.graphiks.kextract.clang.CursorKind
import org.graphiks.kextract.clang.Diagnostic
import org.graphiks.kextract.clang.EvalResult
import org.graphiks.kextract.clang.Index
import org.graphiks.kextract.clang.LibClang
import org.graphiks.kextract.clang.TranslationUnit

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

internal class MacroParserImpl private constructor(
    private val reparser: ClangReparser,
    private val treeMaker: TreeMaker,
    val logger: Logger
) : AutoCloseable {

    val macroTable: MacroTable = MacroTable()

    companion object {
        fun make(treeMaker: TreeMaker, logger: Logger, tu: TranslationUnit, args: Collection<String>): MacroParserImpl {
            val reparser: ClangReparser = try {
                ClangReparser(tu, args, logger)
            } catch (ex: IOException) {
                throw RuntimeException(ex)
            } catch (ex: Index.ParsingFailedException) {
                throw RuntimeException(ex)
            }
            return MacroParserImpl(reparser, treeMaker, logger)
        }
    }

    /**
     * This method attempts to evaluate the macro. Evaluation occurs in two steps: first, an attempt is made
     * to see if the macro corresponds to a simple numeric constant. If so, the constant is parsed in Java directly.
     * If that is not possible (e.g. because the macro refers to other macro, or has a more complex grammar), fall
     * back to use clang evaluation support.
     */
    fun parseConstant(cursor: Cursor, name: String, tokens: Array<String>): Declaration.Constant? {
        if (cursor.isMacroFunctionLike()) {
            return null
        } else if (tokens.size == 2) {
            // check for fast path
            val num = toNumber(tokens[1])
            if (num != null) {
                return treeMaker.createMacro(TreeMaker.CursorPosition.of(cursor), name, Type.primitive(Type.Primitive.Kind.Int), num.toLong())
            }
        }
        macroTable.enterMacro(name, tokens, TreeMaker.CursorPosition.of(cursor))
        return null
    }

    private fun toNumber(str: String): Int? {
        return try {
            // Integer.decode supports '#' hex literals which is not valid in C.
            if (str.isNotEmpty() && str[0] != '#') Integer.decode(str) else null
        } catch (nfe: NumberFormatException) {
            null
        }
    }

    /**
     * This class allows client to reparse a snippet of code against a given set of include files.
     * For performance reasons, the set of includes (which comes from the kextract parser) is compiled
     * into a precompiled header, so as to speed to incremental recompilation of the generated snippets.
     */
    class ClangReparser(tu: TranslationUnit, args: Collection<String>, val logger: Logger) {
        companion object {
            private const val MACRO = "kextract\$macro.h"
        }

        val macroIndex: Index = LibClang.createIndex(true)
        val macroUnit: TranslationUnit

        init {
            val precompiled: Path = Files.createTempFile("kextract$", ".pch")
            precompiled.toFile().deleteOnExit()
            tu.save(precompiled)
            val patchedArgs: Array<String> = (listOf(
                // Avoid system search path, use bundled instead
                "-nostdinc",
                "-ferror-limit=0",
                // precompiled header
                "-include-pch", precompiled.toAbsolutePath().toString()
            ) + args).toTypedArray()
            macroUnit = macroIndex.parse(
                MACRO, "",
                this::processDiagnostics,
                false, // add serialization support (needed for macros)
                *patchedArgs
            )
        }

        fun processDiagnostics(diag: Diagnostic) {
            if (System.getProperty("kextract.debug") == "true") {
                logger.info("kextract.debug.macro.error", diag.spelling())
            }
        }

        fun reparse(snippet: String): Cursor {
            macroUnit.reparse(
                this::processDiagnostics,
                Index.UnsavedFile.of(MACRO, snippet)
            )
            return macroUnit.getCursor()
        }
    }

    /**
     * This abstraction is used to collect all macros which could not be interpreted during parseConstant.
     * All unparsed macros in the table can have three different states: UNPARSED, SUCCESS, and FAILURE.
     *
     * The reparsing process:
     * 1. all unparsed macros are added to the table in the UNPARSED state.
     * 2. a snippet for all macros in the UNPARSED state is compiled and the table state is updated
     * 3. a recovery snippet for all macros in the FAILURE state is compiled and the table state is updated again
     * 4. we repeat from (2) until no further progress is made.
     * 5. we return a list of macro which are in the SUCCESS state.
     */
    inner class MacroTable {

        val macrosByMangledName: MutableMap<String, Entry> = linkedMapOf()

        abstract inner class Entry(
            val name: String,
            val tokens: Array<String>,
            val position: Position
        ) {
            open fun mangledName(): String = "kextract\$macro\$$name"

            open fun success(type: Type, value: Any): Entry = throw IllegalStateException()
            open fun failure(type: Type?): Entry = throw IllegalStateException()
            open fun isSuccess(): Boolean = false
            open fun isRecoverableFailure(): Boolean = false
            open fun isUnparsed(): Boolean = false

            open fun update() {
                macrosByMangledName[mangledName()] = this
            }
        }

        inner class Unparsed(name: String, tokens: Array<String>, position: Position) : Entry(name, tokens, position) {
            override fun success(type: Type, value: Any): Entry =
                Success(name, tokens, position, type, value)

            override fun failure(type: Type?): Entry =
                if (type != null) RecoverableFailure(name, tokens, type, position)
                else UnparseableMacro(name, tokens, position)

            override fun isUnparsed(): Boolean = true

            override fun update() {
                throw IllegalStateException()
            }
        }

        inner class RecoverableFailure(
            name: String,
            tokens: Array<String>,
            val type: Type,
            position: Position
        ) : Entry(name, tokens, position) {
            override fun success(type: Type, value: Any): Entry =
                Success(name, tokens, position, this.type, value)

            override fun failure(type: Type?): Entry =
                UnparseableMacro(name, tokens, position)

            override fun isRecoverableFailure(): Boolean = true
        }

        inner class Success(
            name: String,
            tokens: Array<String>,
            position: Position,
            type: Type,
            value: Any
        ) : Entry(name, tokens, position) {
            val constant: Declaration.Constant = treeMaker.createMacro(position, name, type, value)

            override fun isSuccess(): Boolean = true

            fun constant(): Declaration.Constant = constant
        }

        inner class UnparseableMacro(name: String, tokens: Array<String>, position: Position) : Entry(name, tokens, position) {
            override fun update() {
                macrosByMangledName.remove(mangledName())
            }
        }

        fun enterMacro(name: String, tokens: Array<String>, position: Position) {
            val unparsed = Unparsed(name, tokens, position)
            macrosByMangledName[unparsed.mangledName()] = unparsed
        }

        fun reparseConstants(): List<Declaration.Constant> {
            var last = -1
            while (macrosByMangledName.isNotEmpty() && last != macrosByMangledName.size) {
                last = macrosByMangledName.size
                // step 1 - try parsing macros as var declarations
                reparseMacros(false)
                // step 2 - retry failed parsed macros as pointers
                reparseMacros(true)
            }
            return macrosByMangledName.values
                .filterIsInstance<Success>()
                .map { it.constant() }
        }

        fun updateTable(treeMaker: TreeMaker, decl: Cursor) {
            val mangledName = decl.spelling()
            val entry = macrosByMangledName[mangledName]!!
            decl.eval().use { result ->
                val newEntry: Entry = when (result.getKind()) {
                    EvalResult.Kind.Integral -> {
                        val value = result.getAsInt()
                        entry.success(treeMaker.toType(decl), value)
                    }
                    EvalResult.Kind.FloatingPoint -> {
                        val value = result.getAsFloat()
                        entry.success(treeMaker.toType(decl), value)
                    }
                    EvalResult.Kind.StrLiteral -> {
                        val value = result.getAsString()
                        entry.success(treeMaker.toType(decl), value)
                    }
                    else -> {
                        val type: Type? = if (decl.type().equals(decl.type().canonicalType())) null
                                          else treeMaker.toType(decl)
                        entry.failure(type)
                    }
                }
                newEntry.update()
            }
        }

        fun reparseMacros(recovery: Boolean) {
            val snippet = macroDecl(recovery)
            // note: cursors returned during reparsing are not comparable with existing ones.
            // Because of that, here we create a brand new tree maker, which means pointers to already declared types
            // (e.g. structs, unions, enums) will be downgraded to void*.
            val treeMaker = TreeMaker()
            reparser.reparse(snippet).forEach { c ->
                if (c.kind() == CursorKind.VarDecl && c.spelling().contains("kextract$")) {
                    updateTable(treeMaker, c)
                }
            }
        }

        fun macroDecl(recovery: Boolean): String = buildString {
            if (recovery) append("#include <stdint.h>\n")
            macrosByMangledName.values
                .filter { !it.isSuccess() && (if (recovery) it.isRecoverableFailure() else it.isUnparsed()) }
                .forEach { e ->
                    append("__auto_type ").append(e.mangledName()).append(" = ")
                    if (recovery) append("(uintptr_t)")
                    append(e.name).append(";\n")
                }
        }
    }

    override fun close() {
        reparser.macroUnit.close()
        reparser.macroIndex.close()
    }
}
