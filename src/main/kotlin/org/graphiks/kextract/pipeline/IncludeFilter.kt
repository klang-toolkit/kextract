package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.Type
import java.util.IdentityHashMap

class IncludeFilter(private val includeHelper: IncludeHelper) : Declaration.Visitor<Unit> {

    private var currentParent: Declaration? = null
    private val requiredObjCRecords = IdentityHashMap<Declaration.Scoped, Boolean>()
    private val requiredObjCRecordNames = mutableSetOf<String>()
    private val requiredObjCTypedefs = mutableSetOf<String>()

    fun scan(header: Declaration.Scoped): Declaration.Scoped {
        requiredObjCRecords.clear()
        requiredObjCRecordNames.clear()
        requiredObjCTypedefs.clear()
        collectRequiredObjCTypes(header)
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
            if (name.isNotEmpty() &&
                !isRequiredObjCRecord(d) &&
                !includeHelper.isIncluded(d) &&
                !includeHelper.isIncludedAsTypedef(name)
            ) {
                Skip.with(d)
            }
        }
        val saved = currentParent
        currentParent = d
        d.members().forEach { it.accept(this) }
        currentParent = saved
    }

    override fun visitTypedef(tree: Declaration.Typedef) {
        // Always keep typedefs that alias primitive types, even when filtering is active.
        // These "foundational" typealiases (BOOL, CGFloat, NSInteger, NSUInteger, …) are
        // required by any generated ObjC binding that references them; they must survive
        // --include-objc-class filtering regardless of whether they were explicitly listed.
        if (tree.name() !in requiredObjCTypedefs &&
            !includeHelper.isIncluded(tree) &&
            !isFoundationalTypealias(tree.type())
        ) {
            Skip.with(tree)
        }
    }

    /**
     * Include filters select public declarations by name, but an included Objective-C
     * declaration is not usable unless its value-record typedefs and backing records are
     * emitted too. Discover those dependencies before applying [Skip], including records
     * nested by value inside another surface record.
     */
    private fun collectRequiredObjCTypes(header: Declaration.Scoped) {
        header.members().forEach { declaration ->
            when (declaration) {
                is Declaration.ObjCClass -> if (includeHelper.isIncluded(declaration)) {
                    declaration.methods().forEach(::collectMethodTypes)
                    declaration.properties().forEach { collectType(it.type()) }
                    declaration.ivars().forEach { collectType(it.type()) }
                }
                is Declaration.ObjCProtocol -> if (includeHelper.isIncluded(declaration)) {
                    declaration.methods().forEach(::collectMethodTypes)
                    declaration.properties().forEach { collectType(it.type()) }
                }
                is Declaration.ObjCCategory -> if (includeHelper.isIncluded(declaration)) {
                    declaration.methods().forEach(::collectMethodTypes)
                    declaration.properties().forEach { collectType(it.type()) }
                }
            }
        }
    }

    private fun collectMethodTypes(method: Declaration.ObjCMethod) {
        collectType(method.returnType())
        method.parameters().forEach { collectType(it.type()) }
    }

    private fun collectType(type: Type) {
        when (type) {
            is Type.Delegated -> {
                if (type.kind() == Type.Delegated.Kind.TYPEDEF) {
                    type.name()?.let(requiredObjCTypedefs::add)
                }
                collectType(type.type())
            }
            is Type.Declared -> collectRecord(type.tree())
            is Type.Array -> collectType(type.elementType())
            is Type.Function -> {
                collectType(type.returnType())
                type.argumentTypes().forEach(::collectType)
            }
        }
    }

    private fun collectRecord(record: Declaration.Scoped) {
        if (!record.isStructOrUnion() || requiredObjCRecords.put(record, true) != null) return
        record.name().takeIf(String::isNotEmpty)?.let(requiredObjCRecordNames::add)
        record.members().filterIsInstance<Declaration.Variable>().forEach { collectType(it.type()) }
    }

    private fun isRequiredObjCRecord(record: Declaration.Scoped): Boolean =
        requiredObjCRecords.containsKey(record) || record.name() in requiredObjCRecordNames

    /**
     * Returns true when [type] resolves (through typedef/qualifier chains, not pointer
     * indirections) to a non-void primitive — i.e. the kind of type whose Kotlin
     * typealias must always be emitted so that ObjC bindings compile.
     *
     * Struct-backed typedefs are intentionally excluded: system headers (Foundation,
     * pthreads, Mach) expose struct typedefs whose underlying named structs are
     * excluded by the filter. Keeping the typedef while its backing struct is excluded
     * causes kextract to emit "TYPE depends on UNDERLYING which has been excluded" errors.
     * Primitive-backed typedefs (BOOL, CGFloat, NSInteger, NSUInteger, …) never have
     * this problem because primitives cannot be excluded.
     */
    private fun isFoundationalTypealias(type: Type): Boolean = when {
        type is Type.Primitive && type.kind() != Type.Primitive.Kind.Void -> true
        type is Type.Delegated && type.kind() != Type.Delegated.Kind.POINTER ->
            isFoundationalTypealias(type.type())
        else -> false
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
