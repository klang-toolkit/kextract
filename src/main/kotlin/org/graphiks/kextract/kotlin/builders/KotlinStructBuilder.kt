// src/main/kotlin/org/openjdk/kextract/kotlin/builders/KotlinStructBuilder.kt
package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.utils.TypeMapper

/** Generates Kotlin code for structs and unions. */
class KotlinStructBuilder(private val builder: SourceBuilder, private val toplevel: KotlinToplevelBuilder) {

    fun visitStruct(decl: Declaration.Scoped) {
        if (toplevel.isObjCSurfaceStruct(decl)) visitObjCSurfaceStruct(decl)
        else visitLegacyRecord(decl, "structLayout")
    }

    /** Preserves the existing C API for records outside Objective-C surfaces. */
    private fun visitLegacyRecord(decl: Declaration.Scoped, layoutFactory: String) {
        val className = toplevel.javaName(decl.name())
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : ${decl.kind()} ${decl.name()}")
        builder.appendLine(" */")
        builder.appendLine("class ${className} {")
        builder.indent()
        builder.appendLine("companion object {")
        builder.indent()
        emitLayout(decl, layoutFactory)
        builder.appendLine("fun allocate(allocator: SegmentAllocator): MemorySegment =")
        builder.appendLine("    allocator.allocate(layout)")
        builder.appendLine()
        builder.appendLine("fun allocateArray(elementCount: Long, allocator: SegmentAllocator): MemorySegment =")
        builder.appendLine("    allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout))")
        builder.appendLine()
        builder.appendLine("fun asSlice(array: MemorySegment, index: Long): MemorySegment =")
        builder.appendLine("    array.asSlice(byteSize * index)")
        builder.appendLine()
        builder.appendLine("fun reinterpret(addr: MemorySegment): MemorySegment =")
        builder.appendLine("    addr.reinterpret(byteSize)")
        builder.appendLine()
        builder.appendLine("fun reinterpret(addr: MemorySegment, elementCount: Long): MemorySegment =")
        builder.appendLine("    addr.reinterpret(byteSize * elementCount)")
        builder.appendLine()
        builder.unindent()
        builder.appendLine("} // End companion object")
        for (field in decl.members().filterIsInstance<Declaration.Variable>()) {
            val fieldName = toplevel.javaName(field.name())
            val fieldType = toplevel.mapType(field.type())
            builder.appendLine()
            if (isArrayType(field.type())) {
                builder.appendLine("fun ${fieldName}(segment: MemorySegment): MemorySegment =")
                builder.appendLine("    segment.asSlice(layout.byteOffset(groupElement(\"${field.name()}\")), layout.select(groupElement(\"${field.name()}\")).byteSize())")
            } else {
                val vhName = "${fieldName}_VH"
                builder.appendLine("val ${vhName}: VarHandle = layout.varHandle(groupElement(\"${field.name()}\"))")
                builder.appendLine()
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

    /** Emits nominal value and pointer wrappers only for structs used by Objective-C APIs. */
    private fun visitObjCSurfaceStruct(decl: Declaration.Scoped) {
        val className = toplevel.javaName(decl.name())
        val fields = decl.members().filterIsInstance<Declaration.Variable>()
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : ${decl.kind()} ${decl.name()}")
        builder.appendLine(" */")
        builder.appendLine("class ${className} internal constructor(internal val segment: MemorySegment) {")
        builder.indent()
        builder.appendLine("companion object {")
        builder.indent()
        emitLayout(decl, "structLayout")
        builder.appendLine("fun allocate(allocator: SegmentAllocator): ${className} =")
        builder.appendLine("    ${className}(allocator.allocate(layout))")
        builder.appendLine()
        builder.appendLine("fun allocateArray(elementCount: Long, allocator: SegmentAllocator): ${className}Pointer =")
        builder.appendLine("    ${className}Pointer(allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout)))")
        builder.appendLine()
        builder.appendLine("internal fun asSlice(array: MemorySegment, index: Long): ${className} =")
        builder.appendLine("    ${className}(array.asSlice(byteSize * index, byteSize))")
        builder.appendLine()
        builder.appendLine("internal fun reinterpret(addr: MemorySegment): ${className} =")
        builder.appendLine("    ${className}(addr.reinterpret(byteSize))")
        builder.appendLine()
        builder.appendLine("internal fun reinterpret(addr: MemorySegment, elementCount: Long): ${className}Pointer =")
        builder.appendLine("    ${className}Pointer(addr.reinterpret(byteSize * elementCount))")
        builder.appendLine()
        builder.unindent()
        builder.appendLine("} // End companion object")

        if (fields.isNotEmpty() && fields.all { isConstructibleField(it.type()) }) {
            val parameters = fields.joinToString(", ") { field ->
                val nested = TypeMapper.namedStruct(field.type())
                val type = nested?.let { toplevel.javaName(it.publicName) } ?: toplevel.mapType(field.type())
                "${toplevel.javaName(field.name())}: $type"
            }
            builder.appendLine()
            builder.appendLine("constructor($parameters) : this(Arena.ofAuto().allocate(layout)) {")
            builder.indent()
            for (field in fields) {
                val fieldName = toplevel.javaName(field.name())
                builder.appendLine("$fieldName($fieldName)")
            }
            builder.unindent()
            builder.appendLine("}")
        }

        for (field in fields) {
            val fieldName = toplevel.javaName(field.name())
            val nested = TypeMapper.namedStruct(field.type())
            builder.appendLine()
            when {
                isArrayType(field.type()) -> emitObjCArrayField(fieldName, field)
                nested != null -> emitObjCNestedStructField(fieldName, field, nested)
                else -> emitObjCScalarField(fieldName, field)
            }
        }

        builder.unindent()
        builder.appendLine("} // End class")
        builder.appendLine()
        builder.appendLine("class ${className}Pointer internal constructor(internal val segment: MemorySegment) {")
        builder.indent()
        builder.appendLine("fun pointed(index: Long = 0L): ${className} {")
        builder.indent()
        builder.appendLine("val offset = ${className}.byteSize * index")
        builder.appendLine("val bytes = ${className}.byteSize * (index + 1L)")
        builder.appendLine("return ${className}(segment.reinterpret(bytes).asSlice(offset, ${className}.byteSize))")
        builder.unindent()
        builder.appendLine("}")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    private fun emitLayout(decl: Declaration.Scoped, layoutFactory: String) {
        val fields = decl.members().filterIsInstance<Declaration.Variable>()
        builder.appendLine("val layout: GroupLayout = MemoryLayout.$layoutFactory(")
        builder.indent()
        fields.forEachIndexed { index, field ->
            val comma = if (index < fields.lastIndex) "," else ""
            builder.appendLine("${toplevel.layoutString(field.type())}.withName(\"${field.name()}\")$comma")
        }
        builder.unindent()
        builder.appendLine(").withName(\"${decl.name()}\")")
        builder.appendLine()
        builder.appendLine("val byteSize: Long")
        builder.indent()
        builder.appendLine("get() = layout.byteSize()")
        builder.unindent()
        builder.appendLine()
    }

    private fun emitObjCArrayField(fieldName: String, field: Declaration.Variable) {
        builder.appendLine("fun ${fieldName}(): MemorySegment =")
        builder.appendLine("    segment.asSlice(layout.byteOffset(groupElement(\"${field.name()}\")), layout.select(groupElement(\"${field.name()}\")).byteSize())")
        builder.appendLine()
        builder.appendLine("val ${fieldName}: MemorySegment")
        builder.indent()
        builder.appendLine("get() = ${fieldName}()")
        builder.unindent()
    }

    private fun emitObjCNestedStructField(
        fieldName: String,
        field: Declaration.Variable,
        nested: TypeMapper.NamedRecord,
    ) {
        val nestedName = toplevel.javaName(nested.publicName)
        val offset = "layout.byteOffset(groupElement(\"${field.name()}\"))"
        builder.appendLine("fun ${fieldName}(): ${nestedName} =")
        builder.appendLine("    ${nestedName}(segment.asSlice($offset, ${nestedName}.byteSize))")
        builder.appendLine()
        builder.appendLine("fun ${fieldName}(value: ${nestedName}) =")
        builder.appendLine("    MemorySegment.copy(value.segment, 0L, segment, $offset, ${nestedName}.byteSize)")
        builder.appendLine()
        builder.appendLine("var ${fieldName}: ${nestedName}")
        builder.indent()
        builder.appendLine("get() = ${fieldName}()")
        builder.appendLine("set(value) = ${fieldName}(value)")
        builder.unindent()
    }

    private fun emitObjCScalarField(fieldName: String, field: Declaration.Variable) {
        val fieldType = toplevel.mapType(field.type())
        val vhName = "${fieldName}_VH"
        builder.appendLine("private val ${vhName}: VarHandle = layout.varHandle(groupElement(\"${field.name()}\"))")
        builder.appendLine()
        builder.appendLine("@Suppress(\"UNCHECKED_CAST\")")
        builder.appendLine("fun ${fieldName}(): ${fieldType} = ${vhName}.get(segment, 0L) as ${fieldType}")
        builder.appendLine()
        builder.appendLine("fun ${fieldName}(value: ${fieldType}) = ${vhName}.set(segment, 0L, value)")
        builder.appendLine()
        builder.appendLine("var ${fieldName}: ${fieldType}")
        builder.indent()
        builder.appendLine("get() = ${fieldName}()")
        builder.appendLine("set(value) = ${fieldName}(value)")
        builder.unindent()
    }

    private fun isArrayType(type: Type): Boolean = when {
        type is Type.Array -> true
        type is Type.Delegated -> isArrayType(type.type())
        else -> false
    }

    private fun isConstructibleField(type: Type): Boolean =
        TypeMapper.namedStruct(type) != null || isScalarField(type)

    private fun isScalarField(type: Type): Boolean = when {
        type is Type.Primitive -> type.kind() != Type.Primitive.Kind.Void
        type is Type.Delegated && type.kind() != Type.Delegated.Kind.POINTER -> isScalarField(type.type())
        else -> false
    }

    fun visitUnion(decl: Declaration.Scoped) {
        builder.appendLine("/**")
        builder.appendLine(" * WARNING: This was originally a C union. Fields overlap in memory!")
        builder.appendLine(" * {@snippet lang=c : ${decl.kind()} ${decl.name()}")
        builder.appendLine(" */")
        visitLegacyRecord(decl, "unionLayout")
    }
}
