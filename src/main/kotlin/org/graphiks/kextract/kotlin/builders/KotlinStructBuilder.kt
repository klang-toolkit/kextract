// src/main/kotlin/org/openjdk/kextract/kotlin/builders/KotlinStructBuilder.kt
package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.utils.TypeMapper

/** Generates Kotlin code for structs and unions. */
class KotlinStructBuilder(private val builder: SourceBuilder, private val toplevel: KotlinToplevelBuilder) {
    private val typeLowerer = ObjCTypeLowerer(toplevel)

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
        val recordLayout = KotlinJvmRecordLayoutPlan.createRecord(decl)
        val fields = recordLayout.members.map(KotlinJvmRecordMemberLayout::field)
        builder.appendLine("/**")
        builder.appendLine(" * {@snippet lang=c : ${decl.kind()} ${decl.name()}")
        builder.appendLine(" */")
        builder.appendLine("class ${className} internal constructor(internal val segment: MemorySegment) {")
        builder.indent()
        builder.appendLine("companion object {")
        builder.indent()
        emitObjCSurfaceLayout(decl, recordLayout)
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

        val constructibleFields = fields.mapNotNull { field ->
            fieldKotlinType(field.type())?.let { field to it }
        }
        if (constructibleFields.isNotEmpty()) {
            val parameters = constructibleFields.joinToString(", ") { (field, type) ->
                "${toplevel.javaName(field.name())}: $type"
            }
            builder.appendLine()
            builder.appendLine("constructor($parameters) : this(Arena.ofAuto().allocate(layout)) {")
            builder.indent()
            for ((field, _) in constructibleFields) {
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
                nested != null -> emitObjCNestedStructField(fieldName, field, nested)
                isSegmentField(field.type()) -> emitObjCSegmentField(fieldName, field)
                else -> emitObjCValueField(fieldName, field)
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
            val fieldLayout = if (toplevel.isObjCSurfaceStruct(decl)) {
                typeLowerer.lower(field.type()).layout
            } else {
                toplevel.layoutString(field.type())
            }
            builder.appendLine("${fieldLayout}.withName(\"${field.name()}\")$comma")
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

    /** Emits the exact byte-addressable Clang layout, including bitfield/padding gaps. */
    private fun emitObjCSurfaceLayout(
        decl: Declaration.Scoped,
        recordLayout: KotlinJvmRecordLayout,
    ) {
        val requiredAlignment = recordLayout.members.maxOfOrNull { it.alignmentBytes } ?: 1L
        require(recordLayout.alignmentBytes >= requiredAlignment) {
            "Cannot safely emit Objective-C surface struct ${decl.name()}: " +
                "record alignment ${recordLayout.alignmentBytes} is smaller than member alignment " +
                "$requiredAlignment"
        }
        val elements = mutableListOf<String>()
        var cursor = 0L
        for (member in recordLayout.members) {
            require(member.offsetBytes % member.alignmentBytes == 0L) {
                "Cannot safely emit Objective-C surface struct ${decl.name()}: " +
                    "${member.cName} has Clang offset ${member.offsetBytes} but natural alignment " +
                    "${member.alignmentBytes}"
            }
            val gap = member.offsetBytes - cursor
            if (gap > 0L) elements += "MemoryLayout.paddingLayout(${gap}L)"
            val fieldLayout = typeLowerer.lower(member.field.type()).layout
            elements += "$fieldLayout.withByteAlignment(${member.alignmentBytes}L).withName(\"${member.cName}\")"
            cursor = member.offsetBytes + member.sizeBytes
        }
        val trailing = recordLayout.sizeBytes - cursor
        if (trailing > 0L) elements += "MemoryLayout.paddingLayout(${trailing}L)"
        require(elements.isNotEmpty()) {
            "${decl.name()} has no byte-addressable fields or storage"
        }

        builder.appendLine("val layout: GroupLayout = MemoryLayout.structLayout(")
        builder.indent()
        elements.forEachIndexed { index, element ->
            val comma = if (index < elements.lastIndex) "," else ""
            builder.appendLine("$element$comma")
        }
        builder.unindent()
        builder.appendLine(").withByteAlignment(${recordLayout.alignmentBytes}L).withName(\"${decl.name()}\")")
        builder.appendLine()
        builder.appendLine("val byteSize: Long")
        builder.indent()
        builder.appendLine("get() = layout.byteSize()")
        builder.unindent()
        builder.appendLine()
    }

    private fun emitObjCSegmentField(fieldName: String, field: Declaration.Variable) {
        val offset = "layout.byteOffset(groupElement(\"${field.name()}\"))"
        val size = "layout.select(groupElement(\"${field.name()}\")).byteSize()"
        builder.appendLine("fun ${fieldName}(): MemorySegment =")
        builder.appendLine("    segment.asSlice($offset, $size)")
        builder.appendLine()
        builder.appendLine("fun ${fieldName}(value: MemorySegment) =")
        builder.appendLine("    MemorySegment.copy(value, 0L, segment, $offset, $size)")
        builder.appendLine()
        builder.appendLine("var ${fieldName}: MemorySegment")
        builder.indent()
        builder.appendLine("get() = ${fieldName}()")
        builder.appendLine("set(value) = ${fieldName}(value)")
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

    private fun emitObjCValueField(fieldName: String, field: Declaration.Variable) {
        val lowering = typeLowerer.lower(field.type())
        val fieldType = lowering.kotlinType
        val vhName = "${fieldName}_VH"
        builder.appendLine("private val ${vhName}: VarHandle = layout.varHandle(groupElement(\"${field.name()}\"))")
        builder.appendLine()
        builder.appendLine("fun ${fieldName}(): ${fieldType} = ${lowering.reconstruct("${vhName}.get(segment, 0L)")}")
        builder.appendLine()
        builder.appendLine("fun ${fieldName}(value: ${fieldType}) =")
        builder.appendLine("    ${vhName}.set(segment, 0L, ${lowering.lowerArgument("value")})")
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

    private fun fieldKotlinType(type: Type): String? {
        val nested = TypeMapper.namedStruct(type)
        return when {
            nested != null -> toplevel.javaName(nested.publicName)
            isSegmentField(type) -> "MemorySegment"
            else -> typeLowerer.lower(type).takeUnless { it.isVoid }?.kotlinType
        }
    }

    private fun isSegmentField(type: Type): Boolean {
        if (isArrayType(type)) return true
        val record = resolveRecord(type) ?: return false
        return record.kind() == Declaration.Scoped.Kind.UNION || TypeMapper.namedStruct(type) == null
    }

    private fun resolveRecord(type: Type): Declaration.Scoped? = when {
        type is Type.Declared &&
            (type.tree().kind() == Declaration.Scoped.Kind.STRUCT ||
                type.tree().kind() == Declaration.Scoped.Kind.UNION) -> type.tree()
        type is Type.Delegated && type.kind() != Type.Delegated.Kind.POINTER ->
            resolveRecord(type.type())
        else -> null
    }

    fun visitUnion(decl: Declaration.Scoped) {
        builder.appendLine("/**")
        builder.appendLine(" * WARNING: This was originally a C union. Fields overlap in memory!")
        builder.appendLine(" * {@snippet lang=c : ${decl.kind()} ${decl.name()}")
        builder.appendLine(" */")
        visitLegacyRecord(decl, "unionLayout")
    }
}
