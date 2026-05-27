package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip

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
        if (d.isStructOrUnion()) {
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
