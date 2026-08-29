package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Declaration.Scoped
import org.graphiks.kextract.Declaration.Typedef
import org.graphiks.kextract.Declaration.Variable
import org.graphiks.kextract.Position
import org.graphiks.kextract.Type
import org.graphiks.kextract.Type.Declared
import org.graphiks.kextract.TypeImpl
import org.graphiks.kextract.DeclarationImpl
import org.graphiks.kextract.clang.Cursor
import org.graphiks.kextract.clang.CursorKind
import org.graphiks.kextract.clang.CursorLanguage
import org.graphiks.kextract.clang.EvalResult
import org.graphiks.kextract.clang.LinkageKind
import org.graphiks.kextract.clang.PrintingPolicy
import org.graphiks.kextract.clang.PrintingPolicyProperty
import org.graphiks.kextract.clang.SourceLocation
import org.graphiks.kextract.clang.TypeKind
import org.graphiks.kextract.DeclarationImpl.AnonymousStruct
import org.graphiks.kextract.DeclarationImpl.ClangAlignOf
import org.graphiks.kextract.DeclarationImpl.ClangUnnamedRecord
import org.graphiks.kextract.DeclarationImpl.ClangOffsetOf
import org.graphiks.kextract.DeclarationImpl.ClangSizeOf
import org.graphiks.kextract.DeclarationImpl.NestedDeclarations
import org.graphiks.kextract.DeclarationImpl.DeclarationString
import org.graphiks.kextract.DeclarationImpl.SourceComment
import org.graphiks.kextract.DeclarationImpl.TypedefEnumScoped

import java.nio.file.Path

/**
 * Kotlin port of TreeMaker.
 * All declarations are de-duplicated based on the declaration position.
 */
internal class TreeMaker {

    private val declarationCacheNew: MutableMap<Cursor.Key, Declaration> = mutableMapOf()

    fun addAttributes(d: Declaration?, c: Cursor): Declaration? {
        if (d == null) return null
        val rawComment = c.rawCommentText().trim()
        val briefComment = c.briefCommentText().trim()
        if (rawComment.isNotEmpty() || briefComment.isNotEmpty()) {
            SourceComment.with(d, rawComment, briefComment)
        }
        val attributes: MutableMap<String, MutableList<String>> = mutableMapOf()
        c.forEach { child ->
            if (child.isAttribute()) {
                val attrs = attributes.getOrPut(child.kind().name) { mutableListOf() }
                attrs.add(child.spelling())
            }
        }
        if (attributes.isNotEmpty()) {
            d.addAttribute(Declaration.ClangAttributes(attributes.toMap()))
        }
        val availability = c.platformAvailability()
        if (availability.isNotEmpty()) {
            d.addAttribute(Declaration.PlatformAvailability(availability))
        }
        return d
    }

    fun lookup(key: Cursor.Key): Declaration? {
        return declarationCacheNew[key]
    }

    fun findUniqueScoped(kind: Declaration.Scoped.Kind, name: String): Declaration.Scoped? {
        var match: Declaration.Scoped? = null
        for (declaration in declarationCacheNew.values) {
            if (declaration !is Declaration.Scoped ||
                declaration.kind() != kind || declaration.name() != name) {
                continue
            }
            if (match == null) {
                match = declaration
            } else if (match !== declaration) {
                return null
            }
        }
        return match
    }

    fun createTree(c: Cursor): Declaration? {
        val lang: CursorLanguage = c.language()
        val linkage: LinkageKind = c.linkage()

        if (lang != CursorLanguage.C && lang != CursorLanguage.ObjC &&
            lang != CursorLanguage.Invalid && c.kind() != CursorKind.StaticAssert) {
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
        if (cachedDecl != null) {
            return cachedDecl
        }
        val decl: Declaration? = when (c.kind()) {
            CursorKind.EnumDecl -> createEnum(c)
            CursorKind.EnumConstantDecl -> createEnumConstant(c)
            CursorKind.FieldDecl -> {
                val name = c.spelling()
                if (name.isEmpty() || name.startsWith("struct (anonymous") || name.startsWith("union (anonymous"))
                    null
                else
                    createVar(c, Declaration.Variable.Kind.FIELD)
            }
            CursorKind.ParmDecl -> createVar(c, Declaration.Variable.Kind.PARAMETER)
            CursorKind.FunctionDecl -> createFunction(c)
            CursorKind.StructDecl -> createRecord(c, Declaration.Scoped.Kind.STRUCT)
            CursorKind.UnionDecl -> createRecord(c, Declaration.Scoped.Kind.UNION)
            CursorKind.TypedefDecl -> createTypedefNew(c)
            CursorKind.VarDecl -> createGlobalVar(c)
            // Objective-C declarations
            CursorKind.ObjCInterfaceDecl -> createObjCClass(c)
            CursorKind.ObjCProtocolDecl  -> createObjCProtocol(c)
            CursorKind.ObjCCategoryDecl  -> createObjCCategory(c)
            else -> null
        }
        if (decl != null) {
            declarationCacheNew[key] = withDeclarationStringNew(decl, c)
        }
        return decl
    }

    object CursorPosition {
        fun of(cursor: Cursor): Position {
            val loc: SourceLocation = cursor.getSourceLocation() ?: return Position.NO_POSITION
            val fileLoc = loc.getFileLocation() ?: return Position.NO_POSITION
            return Position(fileLoc.path?.toAbsolutePath(), fileLoc.line, fileLoc.column)
        }
    }

    fun createFunction(c: Cursor): Declaration.Function {
        checkCursorNew(c, CursorKind.FunctionDecl)
        val params: MutableList<Declaration.Variable> = mutableListOf()
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
        } else if (type.isPointer()) {
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
        val pendingFields: MutableList<Declaration> = mutableListOf()
        val pendingBitFields: MutableList<Variable> = mutableListOf()
        var pendingBitfieldsPos: Position? = null

        recordCursor.forEach { fc ->
            if (fc.isFlattenable()) {
                if (fc.isBitField()) {
                    if (pendingBitfieldsPos == null) {
                        pendingBitfieldsPos = CursorPosition.of(fc)
                    }
                    val fieldType = toType(fc)
                    val bitfieldDecl = Declaration.bitfield(CursorPosition.of(fc), fc.spelling(), fc.getBitFieldWidth().toLong(), fieldType)
                    if (fc.spelling().isNotEmpty()) {
                        ClangOffsetOf.with(bitfieldDecl, parent.type().getOffsetOf(fc.spelling()))
                    }
                    pendingBitFields.add(bitfieldDecl)
                } else {
                    if (pendingBitFields.isNotEmpty()) {
                        pendingFields.add(Declaration.bitfields(pendingBitfieldsPos!!, *pendingBitFields.toTypedArray()))
                        pendingBitFields.clear()
                        pendingBitfieldsPos = null
                    }
                    if (fc.isAnonymousStruct()) {
                        pendingFields.add(recordDeclarationNew(parent, fc).tree())
                    } else {
                        val fieldDecl = createTree(fc)!!
                        ClangSizeOf.with(fieldDecl, if (fc.type().kind() == TypeKind.IncompleteArray) 0L else fc.type().size() * 8L)
                        val fieldName = fc.spelling()
                        if (fieldName.isNotEmpty() && !fieldName.startsWith("struct (anonymous") && !fieldName.startsWith("union (anonymous")) {
                            ClangOffsetOf.with(fieldDecl, parent.type().getOffsetOf(fieldName))
                            ClangAlignOf.with(fieldDecl, fc.type().align() * 8L)
                        }
                        pendingFields.add(fieldDecl)
                    }
                }
            } else {
                createTree(fc)
            }
        }

        if (pendingBitFields.isNotEmpty()) {
            pendingFields.add(Declaration.bitfields(pendingBitfieldsPos!!, *pendingBitFields.toTypedArray()))
            pendingBitFields.clear()
            pendingBitfieldsPos = null
        }

        val structOrUnionDecl: Scoped = if (recordCursor.kind() == CursorKind.StructDecl) {
            Declaration.struct(CursorPosition.of(recordCursor), recordCursor.spelling(), *pendingFields.toTypedArray())
        } else {
            Declaration.union(CursorPosition.of(recordCursor), recordCursor.spelling(), *pendingFields.toTypedArray())
        }
        ClangSizeOf.with(structOrUnionDecl, recordCursor.type().size() * 8L)
        ClangAlignOf.with(structOrUnionDecl, recordCursor.type().align() * 8L)
        if (recordCursor.isAnonymous()) {
            ClangUnnamedRecord.with(structOrUnionDecl)
        }
        if (recordCursor.isAnonymousStruct()) {
            AnonymousStruct.with(structOrUnionDecl, offsetOfAnonymousRecordNew(parent, recordCursor, recordCursor))
        }

        return Type.declared(structOrUnionDecl)
    }

    private fun offsetOfAnonymousRecordNew(outermostParent: Cursor, anonRecord: Cursor, record: Cursor): Long? {
        var result: Long? = null
        record.forEachShortCircuit { fc ->
            if (fc.isFlattenable()) {
                if (fc.spelling().isNotEmpty() && !fc.spelling().startsWith("struct (anonymous") && !fc.spelling().startsWith("union (anonymous")) {
                    val offsetToOutermost = outermostParent.type().getOffsetOf(fc.spelling())
                    val offsetToAnon = anonRecord.type().getOffsetOf(fc.spelling())
                    result = offsetToOutermost - offsetToAnon
                    false
                } else if (fc.isAnonymousStruct()) {
                    val nestedResult = offsetOfAnonymousRecordNew(outermostParent, anonRecord, fc)
                    if (nestedResult != null) {
                        result = nestedResult
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
        return result
    }

    fun createEnum(c: Cursor): Declaration.Scoped? {
        return if (c.isDefinition()) {
            val decls: MutableList<Declaration> = mutableListOf()
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
        return declarations.filter { d ->
            d.isEnum() ||
            d is Declaration.ObjCClass ||
            d is Declaration.ObjCProtocol ||
            d is Declaration.ObjCCategory ||
            (d.name().isNotEmpty() && !isRedundantTypedefNew(d))
        }
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
        } else if (canonicalType.isPointer()) {
            val pointeeType = (canonicalType as Type.Delegated).type()
            if (pointeeType is Type.Function) {
                funcType = pointeeType
                isFuncPtrType = true
            }
        }
        if (funcType != null) {
            val params: MutableList<String> = mutableListOf()
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
        val typedef = withNestedTypesNew(Declaration.typedef(CursorPosition.of(c), c.spelling(), canonicalType), c, false)

        // Detect typedef-wrapped enums including ObjC fixed-underlying-type enums
        // (typedef enum : long { … } Foo) whose canonical type is a primitive, not Declared.
        // For those, scan cursor children for an EnumDecl to attach the enum scoped.
        if (TypedefEnumScoped.get(typedef) == null) {
            val enumScopedFromType = when (canonicalType) {
                is Type.Declared -> {
                    val tree = canonicalType.tree()
                    if (tree.kind() == Declaration.Scoped.Kind.ENUM) tree else null
                }
                else -> null
            }
            if (enumScopedFromType != null) {
                TypedefEnumScoped.with(typedef, enumScopedFromType)
            } else {
                // Fallback: inspect cursor children for an EnumDecl
                c.forEach { child ->
                    if (child.kind() == CursorKind.EnumDecl && child.isDefinition()) {
                        val enumDecl = createTree(child)
                        if (enumDecl is Declaration.Scoped &&
                            enumDecl.kind() == Declaration.Scoped.Kind.ENUM) {
                            TypedefEnumScoped.with(typedef, enumDecl)
                        }
                    }
                }
            }
        }
        return typedef
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

    private fun createGlobalVar(c: Cursor): Declaration {
        if (c.isBitField()) throw AssertionError("Cannot get here!")
        checkCursorNew(c, CursorKind.VarDecl)
        val type = toType(c)
        if (c.type().isConstQualifierdType()) {
            val initializer = c.getVarDeclInitializer()
            val evaluated = if (initializer.isInvalid()) c.eval() else initializer.eval()
            evaluated.use { result ->
                if (result.getKind() == EvalResult.Kind.Integral) {
                    return Declaration.constant(CursorPosition.of(c), c.spelling(), result.getAsInt(), type)
                }
            }
        }
        return withNestedTypesNew(Declaration.`var`(Declaration.Variable.Kind.GLOBAL, CursorPosition.of(c), c.spelling(), type), c, false)
    }

    private fun <D : Declaration> withNestedTypesNew(d: D, c: Cursor, ignoreNestedParams: Boolean): D {
        val nestedDefinitions: MutableList<Declaration> = mutableListOf()
        collectNestedTypesNew(c, nestedDefinitions, ignoreNestedParams)
        val nestedDecls = nestedDefinitions.filterIsInstance<Scoped>()
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

    fun toType(t: org.graphiks.kextract.clang.Type): Type {
        return TypeMaker.makeType(t, this)
    }

    private fun checkCursorNew(c: Cursor, k: CursorKind) {
        if (c.kind() != k) {
            throw IllegalArgumentException("Invalid cursor kind")
        }
    }

    private fun checkCursorAnyNew(c: Cursor, vararg kinds: CursorKind) {
        val expected = c.kind()
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

    // ── Objective-C declaration builders ─────────────────────────────────────

    /** Build Declaration.ObjCClass from an ObjCInterfaceDecl cursor. */
    private fun createObjCClass(c: Cursor): Declaration.ObjCClass? {
        // In ObjC, @interface is the header declaration and @implementation is the "definition"
        // in clang's sense. When parsing headers without @implementation, isDefinition() is
        // always false, so we cannot rely on it.
        //
        // Strategy: skip this cursor only if a DIFFERENT canonical definition exists elsewhere
        // (i.e., this is a @class Foo; forward-ref whose @interface is defined elsewhere, or a
        // duplicate @interface). When getDefinition() is invalid there is no other definition and
        // we process this cursor as the canonical interface.
        val defCursor = c.getDefinition()
        if (!defCursor.isInvalid() && !c.equalCursor(defCursor)) {
            // A canonical definition exists elsewhere; this is a forward-ref or redeclaration.
            return null
        }
        var superClass: String? = null
        val protocols = mutableListOf<String>()
        val methods = mutableListOf<Declaration.ObjCMethod>()
        val properties = mutableListOf<Declaration.ObjCProperty>()
        val ivars = mutableListOf<Declaration.Variable>()
        c.forEach { child ->
            when (child.kindOrNull()) {
                CursorKind.ObjCSuperClassRef    -> superClass = child.spelling()
                CursorKind.ObjCProtocolRef      -> protocols.add(child.spelling())
                CursorKind.ObjCInstanceMethodDecl -> createObjCMethod(child, false)?.let { methods.add(it) }
                CursorKind.ObjCClassMethodDecl  -> createObjCMethod(child, true)?.let { methods.add(it) }
                CursorKind.ObjCPropertyDecl     -> createObjCProperty(child)?.let { properties.add(it) }
                CursorKind.ObjCIvarDecl         -> {
                    val ivarType = toType(child.type())
                    ivars.add(Declaration.`var`(Declaration.Variable.Kind.FIELD, CursorPosition.of(child), child.spelling(), ivarType))
                }
                else -> {} // Unknown cursor kinds (e.g. OverloadedDeclRef) — skip
            }
        }
        return Declaration.objcClass(CursorPosition.of(c), c.spelling(), superClass, protocols, methods, properties, ivars)
    }

    /** Build Declaration.ObjCProtocol from an ObjCProtocolDecl cursor. */
    private fun createObjCProtocol(c: Cursor): Declaration.ObjCProtocol? {
        // Same rationale as createObjCClass: @protocol ... @end has isDefinition()==false
        // in many libclang configurations. Process the cursor unless a different canonical
        // definition exists elsewhere.
        val defCursor = c.getDefinition()
        if (!defCursor.isInvalid() && !c.equalCursor(defCursor)) return null
        val protocols = mutableListOf<String>()
        val methods = mutableListOf<Declaration.ObjCMethod>()
        val properties = mutableListOf<Declaration.ObjCProperty>()
        c.forEach { child ->
            when (child.kindOrNull()) {
                CursorKind.ObjCProtocolRef         -> protocols.add(child.spelling())
                CursorKind.ObjCInstanceMethodDecl  -> createObjCMethod(child, false)?.let { methods.add(it) }
                CursorKind.ObjCClassMethodDecl     -> createObjCMethod(child, true)?.let { methods.add(it) }
                CursorKind.ObjCPropertyDecl        -> createObjCProperty(child)?.let { properties.add(it) }
                else -> {} // Unknown cursor kinds — skip safely
            }
        }
        return Declaration.objcProtocol(CursorPosition.of(c), c.spelling(), protocols, methods, properties)
    }

    /**
     * Build Declaration.ObjCCategory from an ObjCCategoryDecl cursor.
     *
     * For "@interface ClassName (CatName)":
     *  - c.spelling()     returns the **category name** ("CatName"), not the class name.
     *  - The extended class name is carried by the first ObjCClassRef child cursor.
     *  - c.displayName()  is not reliable across all libclang versions.
     *
     * We scan child cursors for ObjCClassRef to find the extended class, and use c.spelling()
     * as the category name (may be empty for anonymous/unnamed categories).
     */
    private fun createObjCCategory(c: Cursor): Declaration.ObjCCategory? {
        val catName = c.spelling()   // libclang returns the category name here
        var extendedClass = catName  // fallback if no ObjCClassRef child is found
        val methods = mutableListOf<Declaration.ObjCMethod>()
        val properties = mutableListOf<Declaration.ObjCProperty>()
        c.forEach { child ->
            when (child.kindOrNull()) {
                CursorKind.ObjCClassRef           -> extendedClass = child.spelling()
                CursorKind.ObjCInstanceMethodDecl -> createObjCMethod(child, false)?.let { methods.add(it) }
                CursorKind.ObjCClassMethodDecl    -> createObjCMethod(child, true)?.let { methods.add(it) }
                CursorKind.ObjCPropertyDecl       -> createObjCProperty(child)?.let { properties.add(it) }
                else -> {} // Unknown cursor kinds — skip safely
            }
        }
        val declName = if (catName.isEmpty()) extendedClass else "${extendedClass}_${catName}"
        return Declaration.objcCategory(CursorPosition.of(c), declName, extendedClass, catName, methods, properties)
    }

    /** Build Declaration.ObjCMethod from an ObjCInstanceMethodDecl or ObjCClassMethodDecl cursor. */
    private fun createObjCMethod(c: Cursor, isClassMethod: Boolean): Declaration.ObjCMethod? {
        val selector = c.spelling()   // libclang returns the full selector here
        val numArgs = c.numberOfArgs()
        val params = mutableListOf<Declaration.Variable>()
        for (i in 0 until numArgs) {
            val argCursor = c.getArgument(i)
            val argName = argCursor.spelling().ifEmpty { "arg$i" }
            val argType = toType(argCursor.type())
            params.add(Declaration.parameter(CursorPosition.of(argCursor), argName, argType))
        }
        // Return type — use clang_getCursorResultType (c.type() returns Invalid for ObjC methods)
        val resultClangType = c.resultType()
        val returnType = toType(resultClangType)
        val returnTypeSpelling = resultClangType.spelling()
        return addAttributes(Declaration.objcMethod(
            CursorPosition.of(c), selector, selector, isClassMethod,
            returnType, returnTypeSpelling, params, c.isObjCOptional()
        ), c) as Declaration.ObjCMethod
    }

    /** Build Declaration.ObjCProperty from an ObjCPropertyDecl cursor. */
    private fun createObjCProperty(c: Cursor): Declaration.ObjCProperty? {
        val attrs = c.getObjCPropertyAttributes()
        val isReadOnly = (attrs and 1) != 0   // CXObjCPropertyAttr_readonly = 1
        val isClassProperty = (attrs and 4096) != 0 // CXObjCPropertyAttr_class = 4096
        val propClangType = c.type()
        val type = toType(propClangType)
        val typeSpelling = propClangType.spelling()
        val propName = c.spelling()
        val getter = c.getObjCPropertyGetterName().ifEmpty { propName }
        val setter = if (isReadOnly) "" else
            c.getObjCPropertySetterName().ifEmpty {
                "set${propName.replaceFirstChar { it.uppercaseChar() }}:"
            }
        return addAttributes(Declaration.objcProperty(
            CursorPosition.of(c), propName, type, typeSpelling,
            c.isObjCOptional(), isReadOnly, getter, setter, isClassProperty,
            isObjectiveCObjectReference(propClangType),
        ), c) as Declaration.ObjCProperty
    }

    /**
     * Classifies the exact property type rather than its lowered pointer carrier or spelling.
     * Canonicalization unwraps typedefs/attributes while preserving additional pointer depth.
     */
    private fun isObjectiveCObjectReference(type: org.graphiks.kextract.clang.Type): Boolean {
        return when (type.canonicalType().kind()) {
            TypeKind.ObjCId,
            TypeKind.ObjCClass,
            TypeKind.ObjCInterface,
            TypeKind.ObjCObjectPointer,
            TypeKind.ObjCObject,
            TypeKind.ObjCTypeParam -> true
            else -> false
        }
    }
}
