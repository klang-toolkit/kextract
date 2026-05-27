package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip

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
