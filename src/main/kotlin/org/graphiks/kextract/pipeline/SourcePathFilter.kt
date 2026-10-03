package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.Skip
import java.nio.file.Path

/**
 * Drops every declaration whose source file is not under one of [roots].
 *
 * Used to keep a translation unit that pulls in system headers (for example Dawn's
 * `dawn/webgpu.h`, which includes `<math.h>`) from generating bindings for those
 * system declarations. A declaration with an unknown source path is kept.
 */
class SourcePathFilter(roots: List<Path>) : Declaration.Visitor<Unit> {
    private val normalizedRoots: List<Path> = roots
        .map { it.toAbsolutePath().normalize() }

    fun scan(header: Declaration.Scoped): Declaration.Scoped {
        if (normalizedRoots.isEmpty()) return header
        header.members().forEach { it.accept(this) }
        return header
    }

    override fun visitScoped(d: Declaration.Scoped) {
        dropIfOutside(d)
        d.members().forEach { it.accept(this) }
    }

    override fun visitFunction(d: Declaration.Function) = dropIfOutside(d)

    override fun visitVariable(d: Declaration.Variable) = dropIfOutside(d)

    override fun visitConstant(d: Declaration.Constant) = dropIfOutside(d)

    override fun visitTypedef(d: Declaration.Typedef) = dropIfOutside(d)

    override fun visitObjCClass(d: Declaration.ObjCClass) = dropIfOutside(d)

    override fun visitObjCProtocol(d: Declaration.ObjCProtocol) = dropIfOutside(d)

    override fun visitObjCCategory(d: Declaration.ObjCCategory) = dropIfOutside(d)

    private fun dropIfOutside(declaration: Declaration) {
        val path = declaration.pos().path?.toAbsolutePath()?.normalize() ?: return
        if (normalizedRoots.none { path.startsWith(it) }) {
            Skip.with(declaration)
        }
    }
}
