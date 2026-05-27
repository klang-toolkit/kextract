package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Declaration.Constant
import org.graphiks.kextract.Declaration.Function
import org.graphiks.kextract.Declaration.Scoped
import org.graphiks.kextract.Declaration.Scoped.Kind
import org.graphiks.kextract.Declaration.Typedef
import org.graphiks.kextract.Declaration.Variable
import org.graphiks.kextract.Position
import org.graphiks.kextract.Type
import org.graphiks.kextract.Type.Declared
import org.graphiks.kextract.TypeImpl
import org.graphiks.kextract.DeclarationImpl.AnonymousStruct
import org.graphiks.kextract.DeclarationImpl.ClangSizeOf
import org.graphiks.kextract.DeclarationImpl.Skip

class UnsupportedFilter(private val logger: Logger) : Declaration.Visitor<Unit> {

    private var firstNamedParent: Declaration? = null

    fun scan(header: Scoped): Scoped {
        header.members().forEach { it.accept(this) }
        return header
    }

    override fun visitFunction(funcTree: Function) {
        if (Skip.isPresent(funcTree)) return
        funcTree.forEachNested { it.accept(this) }

        val unsupportedType = firstUnsupportedType(funcTree.type(), false)
        if (unsupportedType != null) {
            warnSkip(funcTree.pos(), funcTree.name(), unsupportedType(unsupportedType))
            Skip.with(funcTree)
            return
        }

        for (param in funcTree.parameters()) {
            param.forEachNested { it.accept(this) }
            val f = param.type().asFunctionPointer()
            if (f != null && !checkFunctionTypeSupported(param, f, funcTree.name())) {
                Skip.with(funcTree)
                return
            }
        }

        val returnFunc = funcTree.type().returnType().asFunctionPointer()
        if (returnFunc != null && !checkFunctionTypeSupported(funcTree, returnFunc, funcTree.name())) {
            Skip.with(funcTree)
        }
    }

    override fun visitVariable(varTree: Variable) {
        if (Skip.isPresent(varTree)) return

        val incomingParent = firstNamedParent
        val saved = firstNamedParent
        firstNamedParent = varTree
        varTree.forEachNested { it.accept(this) }
        firstNamedParent = saved

        val name = fieldName(incomingParent, varTree)
        val unsupportedType = firstUnsupportedType(varTree.type(), false)
        if (unsupportedType != null) {
            warnSkip(varTree.pos(), name, unsupportedType(unsupportedType))
            Skip.with(varTree)
            return
        }

        val func = varTree.type().asFunctionPointer()
        if (func != null && !checkFunctionTypeSupported(varTree, func, name)) {
            Skip.with(varTree)
        }
    }

    override fun visitScoped(scoped: Scoped) {
        if (Skip.isPresent(scoped)) return

        val unsupportedType = firstUnsupportedType(Type.declared(scoped), false)
        if (unsupportedType != null) {
            warnSkip(scoped.pos(), scoped.name(), unsupportedType(unsupportedType))
            Skip.with(scoped)
            return
        }

        if (scoped.kind() == Kind.BITFIELDS) {
            for (bitField in scoped.members()) {
                if (bitField.name().isNotEmpty()) {
                    warnSkip(scoped.pos(), fieldName(firstNamedParent, bitField), unsupportedBitfield())
                }
            }
            Skip.with(scoped)
            return
        }

        val newNamedParent = if (scoped.name().isNotEmpty()) scoped else firstNamedParent
        val saved = firstNamedParent
        firstNamedParent = newNamedParent
        scoped.members().forEach { it.accept(this) }
        firstNamedParent = saved
    }

    override fun visitTypedef(typedefTree: Typedef) {
        if (Skip.isPresent(typedefTree)) return

        if (typedefTree.type() is Declared) {
            visitScoped((typedefTree.type() as Declared).tree())
        }

        val unsupportedType = firstUnsupportedType(typedefTree.type(), false)
        if (unsupportedType != null) {
            warnSkip(typedefTree.pos(), typedefTree.name(), unsupportedType(unsupportedType))
            Skip.with(typedefTree)
            return
        }

        val func = typedefTree.type().asFunctionPointer()
        if (func != null && !checkFunctionTypeSupported(typedefTree, func, typedefTree.name())) {
            Skip.with(typedefTree)
        }
    }

    override fun visitConstant(d: Constant) {
        if (Skip.isPresent(d)) return

        val name = fieldName(firstNamedParent, d)
        val unsupportedType = firstUnsupportedType(d.type(), false)
        if (unsupportedType != null) {
            warnSkip(d.pos(), name, unsupportedType(unsupportedType))
            Skip.with(d)
        }
    }

    // ObjC declarations: all types are normalized to MemorySegment pointers — no unsupported types
    override fun visitObjCClass(d: Declaration.ObjCClass) = Unit
    override fun visitObjCProtocol(d: Declaration.ObjCProtocol) = Unit
    override fun visitObjCCategory(d: Declaration.ObjCCategory) = Unit

    private fun checkFunctionTypeSupported(decl: Declaration, func: Type.Function, nameOfSkipped: String): Boolean {
        val unsupportedType = firstUnsupportedType(func, false)
        if (unsupportedType != null) {
            warnSkip(decl.pos(), nameOfSkipped, unsupportedType(unsupportedType))
            return false
        }
        if (func.varargs() && func.argumentTypes().isNotEmpty()) {
            warnSkip(decl.pos(), nameOfSkipped, unsupportedVariadicCallback(decl.name()))
            return false
        }
        return true
    }

    private fun warnSkip(pos: Position, treeName: String, message: String) {
        logger.warn("kextract.skip.unsupported", treeName, message, pos = pos)
    }

    private fun unsupportedType(type: Type): String = logger.format("unsupported.type", type)

    private fun unsupportedVariadicCallback(name: String): String =
        logger.format("unsupported.variadic.callback", name)

    private fun unsupportedBitfield(): String = logger.format("unsupported.bitfields")

    companion object {
        private fun fieldName(firstNamedParent: Declaration?, decl: Declaration): String {
            val prefix = if (firstNamedParent != null) "${firstNamedParent.name()}." else ""
            return prefix + decl.name()
        }

        fun firstUnsupportedType(type: Type, allowVoid: Boolean): Type? = when (type) {
            is Type.Primitive -> when (type.kind()) {
                Type.Primitive.Kind.Char16,
                Type.Primitive.Kind.Float128,
                Type.Primitive.Kind.HalfFloat,
                Type.Primitive.Kind.Int128,
                Type.Primitive.Kind.WChar -> type
                Type.Primitive.Kind.LongDouble -> if (TypeImpl.IS_WINDOWS) null else type
                Type.Primitive.Kind.Void -> if (allowVoid) null else type
                else -> null
            }
            is Type.Function -> {
                for (arg in type.argumentTypes()) {
                    firstUnsupportedType(arg, false)?.let { return it }
                }
                firstUnsupportedType(type.returnType(), true)
            }
            is Declared -> {
                if (type.tree().kind() == Kind.STRUCT || type.tree().kind() == Kind.UNION) {
                    if (!isValidStructOrUnion(type.tree())) type else null
                } else null
            }
            is Type.Delegated -> {
                if (type.kind() != Type.Delegated.Kind.POINTER)
                    firstUnsupportedType(type.type(), allowVoid)
                else null
            }
            is Type.Array -> firstUnsupportedType(type.elementType(), false)
            else -> if (type.isErroneous()) type else null
        }

        private fun isValidStructOrUnion(scoped: Scoped): Boolean {
            if (ClangSizeOf.get(scoped) == null) return false
            if (AnonymousStruct.isPresent(scoped) &&
                AnonymousStruct.getOrThrow(scoped).offset == null) return false
            return true
        }
    }
}
