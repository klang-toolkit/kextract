package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.Type.Delegated
import org.graphiks.kextract.DeclarationImpl.Skip

class MissingDepChecker(private val logger: Logger) : Declaration.Visitor<Unit> {

    private var currentParent: Declaration? = null

    fun scan(header: Declaration.Scoped): Declaration.Scoped {
        header.members().forEach { it.accept(this) }
        return header
    }

    override fun visitFunction(funcTree: Declaration.Function) {
        if (Skip.isPresent(funcTree)) return

        val posDecl = currentParent ?: funcTree
        val saved = currentParent
        currentParent = posDecl
        funcTree.parameters().forEach { it.accept(this) }
        funcTree.forEachNested { it.accept(this) }
        currentParent = saved
        checkMissingDep(posDecl, funcTree.type())
    }

    override fun visitScoped(d: Declaration.Scoped) {
        if (Skip.isPresent(d)) return

        val posDecl = currentParent ?: d
        val saved = currentParent
        currentParent = posDecl
        d.members().forEach { it.accept(this) }
        currentParent = saved
    }

    override fun visitTypedef(tree: Declaration.Typedef) {
        if (Skip.isPresent(tree)) return

        val posDecl = currentParent ?: tree
        val saved = currentParent
        currentParent = posDecl
        tree.forEachNested { it.accept(this) }
        currentParent = saved
        checkMissingDep(posDecl, tree.type())
        tree.type().asFunctionPointer()?.let { checkMissingDep(posDecl, it) }
    }

    override fun visitVariable(tree: Declaration.Variable) {
        if (Skip.isPresent(tree)) return

        val posDecl = currentParent ?: tree
        val saved = currentParent
        currentParent = posDecl
        tree.forEachNested { it.accept(this) }
        currentParent = saved
        checkMissingDep(posDecl, tree.type())
        tree.type().asFunctionPointer()?.let { checkMissingDep(posDecl, it) }
    }

    // ObjC: all types reduce to MemorySegment — no missing dep checks needed
    override fun visitObjCClass(d: Declaration.ObjCClass) = Unit
    override fun visitObjCProtocol(d: Declaration.ObjCProtocol) = Unit
    override fun visitObjCCategory(d: Declaration.ObjCCategory) = Unit

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
