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
import org.openjdk.kextract.Declaration.Scoped
import org.openjdk.kextract.Declaration.Typedef
import org.openjdk.kextract.Declaration.Variable
import org.openjdk.kextract.Position
import org.openjdk.kextract.Type
import org.openjdk.kextract.Type.Declared
import org.openjdk.kextract.clang.Cursor
import org.openjdk.kextract.clang.CursorKind
import org.openjdk.kextract.clang.CursorLanguage
import org.openjdk.kextract.clang.LinkageKind
import org.openjdk.kextract.clang.PrintingPolicy
import org.openjdk.kextract.clang.PrintingPolicyProperty
import org.openjdk.kextract.clang.SourceLocation
import org.openjdk.kextract.clang.TypeKind
import org.openjdk.kextract.impl.DeclarationImpl.AnonymousStruct
import org.openjdk.kextract.impl.DeclarationImpl.ClangAlignOf
import org.openjdk.kextract.impl.DeclarationImpl.ClangOffsetOf
import org.openjdk.kextract.impl.DeclarationImpl.ClangSizeOf
import org.openjdk.kextract.impl.DeclarationImpl.NestedDeclarations
import org.openjdk.kextract.impl.DeclarationImpl.DeclarationString

import java.nio.file.Path
import java.util.ArrayList
import java.util.Collections
import java.util.HashMap
import java.util.Objects
import java.util.Optional
import java.util.OptionalLong
import java.util.concurrent.atomic.AtomicReference
import java.util.stream.Collectors

/**
 * Kotlin port of TreeMaker.
 * All declarations are de-duplicated based on the declaration position.
 */
internal class TreeMaker {

    private val declarationCacheNew: MutableMap<Cursor.Key, Declaration> = HashMap()

    fun addAttributes(d: Declaration?, c: Cursor): Declaration? {
        if (d == null) return null
        val attributes: MutableMap<String, MutableList<String>> = HashMap()
        c.forEach { child ->
            if (child.isAttribute()) {
                val attrs = attributes.computeIfAbsent(child.kind().name) { ArrayList() }
                attrs.add(child.spelling())
            }
        }
        if (attributes.isNotEmpty()) {
            d.addAttribute(Declaration.ClangAttributes(Collections.unmodifiableMap(attributes)))
        }
        return d
    }

    fun lookup(key: Cursor.Key): Optional<Declaration> {
        return Optional.ofNullable(declarationCacheNew[key])
    }

    fun createTree(c: Cursor): Declaration? {
        Objects.requireNonNull(c)
        val lang: CursorLanguage = c.language()
        val linkage: LinkageKind = c.linkage()

        if (lang != CursorLanguage.C && lang != CursorLanguage.Invalid &&
            c.kind() != CursorKind.StaticAssert) {
            throw RuntimeException("Unsupported language: ${c.language()}")
        }

        if (linkage == LinkageKind.Internal) {
            return null
        }

        if (c.isFunctionInlined()) {
            return null
        }
        val rv = createTreeInternalNew(c) as? DeclarationImpl
        return addAttributes(rv, c)
    }

    private fun createTreeInternalNew(c: Cursor): Declaration? {
        val pos = CursorPosition.of(c)
        if (pos === Position.NO_POSITION) return null
        val key = c.toKey()
        val cachedDecl = lookup(key)
        if (cachedDecl.isPresent) {
            return cachedDecl.get()
        }
        val decl: Declaration? = when (c.kind()) {
            CursorKind.EnumDecl -> createEnum(c)
            CursorKind.EnumConstantDecl -> createEnumConstant(c)
            CursorKind.FieldDecl -> createVar(c, Declaration.Variable.Kind.FIELD)
            CursorKind.ParmDecl -> createVar(c, Declaration.Variable.Kind.PARAMETER)
            CursorKind.FunctionDecl -> createFunction(c)
            CursorKind.StructDecl -> createRecord(c, Declaration.Scoped.Kind.STRUCT)
            CursorKind.UnionDecl -> createRecord(c, Declaration.Scoped.Kind.UNION)
            CursorKind.TypedefDecl -> createTypedefNew(c)
            CursorKind.VarDecl -> createVar(c, Declaration.Variable.Kind.GLOBAL)
            else -> null
        }
        if (decl != null) {
            declarationCacheNew[key] = withDeclarationStringNew(decl, c)
        }
        return decl
    }

    class CursorPosition private constructor(val cursor: Cursor) : Position {
        private val posPath: Path
        private val posLine: Int
        private val posColumn: Int

        init {
            val loc: SourceLocation.Location = cursor.getSourceLocation()!!.getFileLocation()
            posPath = loc.path!!.toAbsolutePath()
            posLine = loc.line
            posColumn = loc.column
        }

        override fun path(): Path = posPath
        override fun line(): Int = posLine
        override fun col(): Int = posColumn
        fun cursor(): Cursor = cursor

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other is Position) {
                return Objects.equals(posPath, other.path()) &&
                    Objects.equals(posLine, other.line()) &&
                    Objects.equals(posColumn, other.col())
            }
            return false
        }

        override fun hashCode(): Int = Objects.hash(posPath, posLine, posColumn)

        override fun toString(): String = PrettyPrinter.position(this)

        companion object {
            @JvmStatic
            fun of(cursor: Cursor): Position {
                val loc: SourceLocation = cursor.getSourceLocation() ?: return Position.NO_POSITION
                loc.getFileLocation() ?: return Position.NO_POSITION
                return CursorPosition(cursor)
            }
        }
    }

    fun createFunction(c: Cursor): Declaration.Function {
        checkCursorNew(c, CursorKind.FunctionDecl)
        val params: MutableList<Declaration.Variable> = ArrayList()
        for (i in 0 until c.numberOfArgs()) {
            params.add(createTree(c.getArgument(i)) as Declaration.Variable)
        }
        val type = toType(c)
        val funcType = canonicalTypeNew(type)
        return withNestedTypesNew(
            Declaration.function(CursorPosition.of(c), c.spelling(), funcType as Type.Function,
                *params.toTypedArray()),
            c, true
        )
    }

    fun createMacro(pos: Position, name: String, type: Type, value: Any): Declaration.Constant {
        val macro = Declaration.constant(pos, name, value, type)
        var valueString = value.toString()
        if (value is String) {
            valueString = "\"$valueString\""
        } else if (Utils.isPointer(type)) {
            valueString = "(void*) $valueString"
        }
        DeclarationString.with(macro, "#define $name $valueString")
        return macro
    }

    fun createEnumConstant(c: Cursor): Declaration.Constant {
        return Declaration.constant(CursorPosition.of(c), c.spelling(), c.getEnumConstantValue(), toType(c))
    }

    fun createHeader(c: Cursor, decls: List<Declaration>): Declaration.Scoped {
        return Declaration.toplevel(CursorPosition.of(c), *filterHeaderDeclarationsNew(decls).toTypedArray())
    }

    fun createRecord(c: Cursor, scopeKind: Declaration.Scoped.Kind): Declaration.Scoped? {
        checkCursorAnyNew(c, CursorKind.StructDecl, CursorKind.UnionDecl)
        return if (c.isDefinition()) {
            val t: Type.Declared = recordDeclarationNew(c, c)
            t.tree()
        } else {
            if (!c.getDefinition().isInvalid()) {
                null
            } else {
                Declaration.scoped(scopeKind, CursorPosition.of(c), c.spelling())
            }
        }
    }

    fun recordDeclarationNew(parent: Cursor, recordCursor: Cursor): Type.Declared {
        val pendingFields: MutableList<Declaration> = ArrayList()
        val pendingBitFields: MutableList<Variable> = ArrayList()
        val pendingBitfieldsPos: AtomicReference<Position?> = AtomicReference(null)

        recordCursor.forEach { fc ->
            if (Utils.isFlattenable(fc)) {
                if (fc.isBitField()) {
                    if (pendingBitfieldsPos.get() == null) {
                        pendingBitfieldsPos.set(CursorPosition.of(fc))
                    }
                    val fieldType = toType(fc)
                    val bitfieldDecl = Declaration.bitfield(CursorPosition.of(fc), fc.spelling(), fc.getBitFieldWidth().toLong(), fieldType)
                    if (fc.spelling().isNotEmpty()) {
                        ClangOffsetOf.with(bitfieldDecl, parent.type().getOffsetOf(fc.spelling()))
                    }
                    pendingBitFields.add(bitfieldDecl)
                } else {
                    if (pendingBitFields.isNotEmpty()) {
                        pendingFields.add(Declaration.bitfields(pendingBitfieldsPos.get()!!, *pendingBitFields.toTypedArray()))
                        pendingBitFields.clear()
                        pendingBitfieldsPos.set(null)
                    }
                    if (fc.isAnonymousStruct()) {
                        pendingFields.add(recordDeclarationNew(parent, fc).tree())
                    } else {
                        val fieldDecl = createTree(fc)!!
                        ClangSizeOf.with(fieldDecl, if (fc.type().kind() == TypeKind.IncompleteArray) 0L else fc.type().size() * 8L)
                        ClangOffsetOf.with(fieldDecl, parent.type().getOffsetOf(fc.spelling()))
                        ClangAlignOf.with(fieldDecl, fc.type().align() * 8L)
                        pendingFields.add(fieldDecl)
                    }
                }
            } else {
                createTree(fc)
            }
        }

        if (pendingBitFields.isNotEmpty()) {
            pendingFields.add(Declaration.bitfields(pendingBitfieldsPos.get()!!, *pendingBitFields.toTypedArray()))
            pendingBitFields.clear()
            pendingBitfieldsPos.set(null)
        }

        val structOrUnionDecl: Scoped = if (recordCursor.kind() == CursorKind.StructDecl) {
            Declaration.struct(CursorPosition.of(recordCursor), recordCursor.spelling(), *pendingFields.toTypedArray())
        } else {
            Declaration.union(CursorPosition.of(recordCursor), recordCursor.spelling(), *pendingFields.toTypedArray())
        }
        ClangSizeOf.with(structOrUnionDecl, recordCursor.type().size() * 8L)
        ClangAlignOf.with(structOrUnionDecl, recordCursor.type().align() * 8L)
        if (recordCursor.isAnonymousStruct()) {
            AnonymousStruct.with(structOrUnionDecl, offsetOfAnonymousRecordNew(parent, recordCursor, recordCursor))
        }

        return Type.declared(structOrUnionDecl)
    }

    private fun offsetOfAnonymousRecordNew(outermostParent: Cursor, anonRecord: Cursor, record: Cursor): OptionalLong {
        val result: AtomicReference<OptionalLong> = AtomicReference(OptionalLong.empty())
        record.forEachShortCircuit { fc ->
            if (Utils.isFlattenable(fc)) {
                if (fc.spelling().isNotEmpty()) {
                    val offsetToOutermost = outermostParent.type().getOffsetOf(fc.spelling())
                    val offsetToAnon = anonRecord.type().getOffsetOf(fc.spelling())
                    result.set(OptionalLong.of(offsetToOutermost - offsetToAnon))
                    false
                } else if (fc.isAnonymousStruct()) {
                    val nestedResult = offsetOfAnonymousRecordNew(outermostParent, anonRecord, fc)
                    if (nestedResult.isPresent) {
                        result.set(nestedResult)
                        false
                    } else {
                        true
                    }
                } else {
                    true
                }
            } else {
                true
            }
        }
        return result.get()
    }

    fun createEnum(c: Cursor): Declaration.Scoped? {
        return if (c.isDefinition()) {
            val decls: MutableList<Declaration> = ArrayList()
            c.forEach { child ->
                if (child.kind() == CursorKind.EnumConstantDecl) {
                    val enumConstantDecl = createTree(child)!!
                    DeclarationString.with(enumConstantDecl, enumConstantStringNew(c.spelling(), enumConstantDecl as Declaration.Constant))
                    decls.add(enumConstantDecl)
                }
            }
            val enumDecl = Declaration.enum_(CursorPosition.of(c), c.spelling(), *decls.toTypedArray())
            DeclarationImpl.ClangEnumType.with(enumDecl, toType(c.getEnumDeclIntegerType()))
            enumDecl
        } else {
            null
        }
    }

    private fun filterHeaderDeclarationsNew(declarations: List<Declaration>): List<Declaration> {
        return declarations.stream()
            .filter { it != null }
            .filter { d -> Utils.isEnum(d) || (d.name().isNotEmpty() && !isRedundantTypedefNew(d)) }
            .collect(Collectors.toList())
    }

    private fun isRedundantTypedefNew(d: Declaration): Boolean {
        return d is Typedef &&
                d.type() is Declared &&
                (d.type() as Declared).tree().name() == d.name()
    }

    private fun createTypedefNew(c: Cursor): Declaration.Typedef {
        val cursorType = toType(c)
        var canonicalType = canonicalTypeNew(cursorType)
        var funcType: Type.Function? = null
        var isFuncPtrType = false
        if (canonicalType is Type.Function) {
            funcType = canonicalType
        } else if (Utils.isPointer(canonicalType)) {
            val pointeeType = (canonicalType as Type.Delegated).type()
            if (pointeeType is Type.Function) {
                funcType = pointeeType
                isFuncPtrType = true
            }
        }
        if (funcType != null) {
            val params: MutableList<String> = ArrayList()
            c.forEach { child ->
                if (child.kind() == CursorKind.ParmDecl) {
                    params.add(createTree(child)!!.name())
                }
            }
            if (params.isNotEmpty()) {
                canonicalType = funcType.withParameterNames(params)
                if (isFuncPtrType) {
                    canonicalType = TypeImpl.PointerImpl(canonicalType)
                }
            }
        }
        return withNestedTypesNew(Declaration.typedef(CursorPosition.of(c), c.spelling(), canonicalType), c, false)
    }

    private fun canonicalTypeNew(t: Type): Type {
        return if (t is Type.Delegated && t.kind() == Type.Delegated.Kind.TYPEDEF) {
            t.type()
        } else {
            t
        }
    }

    private fun createVar(c: Cursor, kind: Declaration.Variable.Kind): Declaration.Variable {
        if (c.isBitField()) throw AssertionError("Cannot get here!")
        checkCursorAnyNew(c, CursorKind.VarDecl, CursorKind.FieldDecl, CursorKind.ParmDecl)
        val type = toType(c)
        return withNestedTypesNew(Declaration.`var`(kind, CursorPosition.of(c), c.spelling(), type), c, false)
    }

    private fun <D : Declaration> withNestedTypesNew(d: D, c: Cursor, ignoreNestedParams: Boolean): D {
        val nestedDefinitions: MutableList<Declaration> = ArrayList()
        collectNestedTypesNew(c, nestedDefinitions, ignoreNestedParams)
        val nestedDecls = nestedDefinitions.stream()
            .filter { m -> m is Scoped }
            .map { m -> m as Scoped }
            .toList()
        if (nestedDecls.isNotEmpty()) {
            NestedDeclarations.with(d, nestedDecls)
        }
        return d
    }

    private fun collectNestedTypesNew(c: Cursor, nestedTypes: MutableList<Declaration>, ignoreNestedParams: Boolean) {
        c.forEach { m ->
            if (m.isDefinition()) {
                if (m.kind() == CursorKind.ParmDecl && !ignoreNestedParams) {
                    collectNestedTypesNew(m, nestedTypes, ignoreNestedParams)
                } else {
                    val decl = createTree(m)
                    if (decl != null) nestedTypes.add(decl)
                }
            }
        }
    }

    fun toType(c: Cursor): Type {
        return TypeMaker.makeType(c.type(), this)
    }

    fun toType(t: org.openjdk.kextract.clang.Type): Type {
        return TypeMaker.makeType(t, this)
    }

    private fun checkCursorNew(c: Cursor, k: CursorKind) {
        if (c.kind() != k) {
            throw IllegalArgumentException("Invalid cursor kind")
        }
    }

    private fun checkCursorAnyNew(c: Cursor, vararg kinds: CursorKind) {
        val expected = Objects.requireNonNull(c.kind())
        for (k in kinds) {
            if (k == expected) return
        }
        throw IllegalArgumentException("Invalid cursor kind")
    }

    private fun <D : Declaration> withDeclarationStringNew(decl: D, cursor: Cursor): D {
        val declString: String? = when (decl) {
            is Declaration.Constant -> null
            is Typedef -> declarationStringNew(cursor, true)
            else -> {
                var cursorString = declarationStringNew(cursor, false)
                if (cursorString.matches(Regex(".*\\((unnamed|anonymous) (struct|union|enum) at.*"))) {
                    cursorString = declarationStringNew(cursor, true)
                }
                cursorString
            }
        }
        if (declString != null) {
            DeclarationString.with(decl, declString)
        }
        return decl
    }

    private fun declarationStringNew(cursor: Cursor, expandNestedDecls: Boolean): String {
        val pp: PrintingPolicy = cursor.getPrintingPolicy()
        if (expandNestedDecls) {
            pp.setProperty(PrintingPolicyProperty.IncludeTagDefinition, true)
        }
        pp.setProperty(PrintingPolicyProperty.PolishForDeclaration, true)
        return cursor.prettyPrinted(pp)
    }

    private fun enumConstantStringNew(enumName: String, enumConstant: Declaration.Constant): String {
        val name = if (enumName.isEmpty()) "<anonymous>" else enumName
        return "enum $name.${enumConstant.name()} = ${enumConstant.value()}"
    }
}
