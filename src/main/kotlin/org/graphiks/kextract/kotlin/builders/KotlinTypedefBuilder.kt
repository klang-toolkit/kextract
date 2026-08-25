// src/main/kotlin/org/openjdk/kextract/kotlin/builders/KotlinTypedefBuilder.kt
package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.utils.TypeMapper

/**
 * Generates Kotlin code for typedefs.
 *
 * Note: NS_ENUM / NS_OPTIONS enums (typedef enum : long { … } Foo) are NOT handled here because
 * clang names the inner enum with the same identifier as the typedef, making the typedef
 * "redundant" and causing it to be filtered before [visitTypedef] is called.  Those enums are
 * instead handled by [KotlinToplevelBuilder.visitScoped] when it encounters
 * [Declaration.Scoped.Kind.ENUM] with a non-empty name.
 *
 * Everything else (function-pointer typedefs, primitive typedefs, struct/union aliases) produces
 * a plain `typealias` here.
 */
class KotlinTypedefBuilder(private val builder: SourceBuilder, private val toplevel: KotlinToplevelBuilder) {

    fun visitTypedef(decl: Declaration.Typedef) {
        // Fallback: plain typealias
        val name = toplevel.javaName(decl.name())
        if (toplevel.hasGeneratedEnum(decl.name())) return
        val pointedStruct = TypeMapper.pointedStruct(decl.type())
        if (pointedStruct != null && toplevel.isObjCSurfacePointerStruct(pointedStruct.declaration)) {
            val target = "${toplevel.javaName(pointedStruct.declaration.name())}Pointer"
            if (name != target) emitTypealias(decl, name, target)
            return
        }
        val struct = TypeMapper.namedStruct(decl.type())
        if (struct != null &&
            toplevel.isObjCSurfacePointerStruct(struct.declaration) &&
            !toplevel.isObjCSurfaceStruct(struct.declaration)
        ) {
            val target = toplevel.javaName(struct.declaration.name())
            if (name != target) {
                val pointerName = "${name}Pointer"
                if (!toplevel.hasObjCSurfacePointerTypedef(pointerName)) {
                    builder.appendLine("typealias $pointerName = ${target}Pointer")
                    builder.appendLine()
                }
            }
        }
        if (struct != null && toplevel.isObjCSurfaceStruct(struct.declaration)) {
            val target = toplevel.javaName(struct.declaration.name())
            if (name != target) {
                emitTypealias(decl, name, target)
                val pointerName = "${name}Pointer"
                if (!toplevel.hasObjCSurfacePointerTypedef(pointerName)) {
                    builder.appendLine("typealias $pointerName = ${target}Pointer")
                    builder.appendLine()
                }
            }
            return
        }
        val type = toplevel.mapType(decl.type())

        // Skip self-referencing typealiases (e.g. `typedef unsigned char Byte` → typealias Byte = Byte)
        if (name == type) return

        emitTypealias(decl, name, type)
    }

    private fun emitTypealias(decl: Declaration.Typedef, name: String, type: String) {
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : typedef ${decl.type()} ${decl.name()};}")
        builder.appendLine(" */")
        builder.appendLine("typealias ${name} = ${type}")
        builder.appendLine()
    }
}
