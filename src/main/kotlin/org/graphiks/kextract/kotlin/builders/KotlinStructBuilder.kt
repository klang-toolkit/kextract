// src/main/kotlin/org/openjdk/kextract/kotlin/builders/KotlinStructBuilder.kt
package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.pipeline.LayoutUtils
import org.graphiks.kextract.kotlin.utils.TypeMapper

/**
 * Generates Kotlin code for structs and unions.
 */
class KotlinStructBuilder(private val builder: SourceBuilder, private val toplevel: KotlinToplevelBuilder) {

    fun visitStruct(decl: Declaration.Scoped) {
        val className = toplevel.javaName(decl.name())

        // KDoc
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : ${decl.kind()} ${decl.name()}")
        builder.appendLine(" */")

        // Class
        builder.appendLine("class ${className} {")
        builder.indent()

        // Companion object (static members)
        builder.appendLine("companion object {")
        builder.indent()

        // Layout
        builder.appendLine("val layout: GroupLayout = MemoryLayout.structLayout(")
        builder.indent()
        val fields = decl.members().filterIsInstance<Declaration.Variable>()
        fields.forEachIndexed { i, field ->
            val layout = LayoutUtils.layoutString(field.type())
            val comma = if (i < fields.count() - 1) "," else ""
            builder.appendLine("${layout}.withName(\"${field.name()}\")${comma}")
        }
        builder.unindent()
        builder.appendLine(").withName(\"${decl.name()}\")")
        builder.appendLine()

        // byteSize
        builder.appendLine("val byteSize: Long")
        builder.indent()
        builder.appendLine("get() = layout.byteSize()")
        builder.unindent()
        builder.appendLine()

        // allocate
        builder.appendLine("fun allocate(allocator: SegmentAllocator): MemorySegment =")
        builder.appendLine("    allocator.allocate(layout)")
        builder.appendLine()
        builder.appendLine("fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =")
        builder.appendLine("    allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))")
        builder.appendLine()

        // asSlice
        builder.appendLine("fun asSlice(array: MemorySegment, index: Long): MemorySegment =")
        builder.appendLine("    array.asSlice(layout.byteSize * index)")
        builder.appendLine()

        // reinterpret
        builder.appendLine("fun reinterpret(addr: MemorySegment): MemorySegment =")
        builder.appendLine("    addr.reinterpret(layout.byteSize)")
        builder.appendLine()
        builder.appendLine("fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =")
        builder.appendLine("    addr.reinterpret(layout.byteSize * elementCount)")
        builder.appendLine()

        builder.unindent()
        builder.appendLine("} // End companion object")

        // Fields (properties + getters/setters using VarHandle for value types, asSlice for array types)
        decl.members().filterIsInstance<Declaration.Variable>().forEach { field ->
            val fieldName = toplevel.javaName(field.name())
            val fieldType = TypeMapper.map(field.type())
            val isArray = isArrayType(field.type())

            builder.appendLine()
            if (isArray) {
                // Array fields: use asSlice to return a sub-segment
                builder.appendLine("fun ${fieldName}(segment: MemorySegment): MemorySegment =")
                builder.appendLine("    segment.asSlice(layout.byteOffset(groupElement(\"${field.name()}\")), layout.select(groupElement(\"${field.name()}\")).byteSize())")
            } else {
                val vhName = "${fieldName}_VH"
                builder.appendLine("val ${vhName}: VarHandle = layout.varHandle(groupElement(\"${field.name()}\"))")
                builder.appendLine()

                // Getter/Setter using VarHandle
                builder.appendLine("@Suppress(\"UNCHECKED_CAST\")")
                builder.appendLine("fun ${fieldName}(segment: MemorySegment): ${fieldType} =")
                builder.appendLine("    ${vhName}.get(segment, 0L) as ${fieldType}")
                builder.appendLine()
                builder.appendLine("fun ${fieldName}(segment: MemorySegment, value: ${fieldType}) =")
                builder.appendLine("    ${vhName}.set(segment, 0L, value)")
            }
        }

        builder.unindent()
        builder.appendLine("} // End class")
        builder.appendLine()
    }

    /**
     * Returns true if the type would produce a SequenceLayout (array type),
     * which cannot use VarHandle directly.
     */
    private fun isArrayType(type: Type): Boolean = when {
        type is Type.Array -> true
        type is Type.Delegated -> isArrayType(type.type())
        else -> false
    }

    fun visitUnion(decl: Declaration.Scoped) {
        // Treat unions as structs (Kotlin has no native union support)
        // Add a warning in KDoc
        builder.appendLine("/**")
        builder.appendLine(" * WARNING: This was originally a C union. Fields overlap in memory!")
        builder.appendLine(" * {@snippet lang=c : ${decl.kind()} ${decl.name()}")
        builder.appendLine(" */")
        visitStruct(decl) // Reuse struct logic
    }
}
