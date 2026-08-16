package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.ClangUnnamedRecord
import org.graphiks.kextract.DeclarationImpl.Skip
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiIndex
import org.graphiks.kextract.kotlin.abi.KotlinKmpCAbiType

/**
 * Memory-backed struct field emission shared by the Android and JVM actual
 * builders. Both targets generate the same accessor shape over a
 * `MemoryBuffer` handle: scalar reads/writes at Clang byte offsets, pointer
 * fields through readPointer/writePointer, nested records as ByValue views
 * plus byte copies on assignment.
 */

internal val memoryScalarPrimitives = setOf(
    "Byte", "UByte", "Short", "UShort", "Int", "UInt", "Long", "ULong", "Float", "Double",
)

/** Kotlin carrier width in bytes for a scalar mapped type; guards 32-bit (armeabi-v7a) over-reads. */
internal fun carrierBytesFor(fieldType: String): Long = when (fieldType) {
    "Byte", "UByte", "Boolean" -> 1L
    "Short", "UShort" -> 2L
    "Int", "UInt", "Float" -> 4L
    "Long", "ULong", "Double" -> 8L
    else -> error("No carrier width for mapped type $fieldType")
}

internal fun memoryPrimitives(fieldType: String): Pair<String, String> = when (fieldType) {
    "Byte" -> "readByte" to "writeByte"
    "UByte" -> "readUByte" to "writeUByte"
    "Short" -> "readShort" to "writeShort"
    "UShort" -> "readUShort" to "writeUShort"
    "Int" -> "readInt" to "writeInt"
    "UInt" -> "readUInt" to "writeUInt"
    "Long" -> "readLong" to "writeLong"
    "ULong" -> "readULong" to "writeULong"
    "Float" -> "readFloat" to "writeFloat"
    "Double" -> "readDouble" to "writeDouble"
    else -> error("No memory primitive for mapped type $fieldType")
}

internal fun memoryPrimitives(scalar: KotlinKmpCAbiType.Scalar): Pair<String, String> =
    when (scalar.kind) {
        KotlinKmpCAbiType.Scalar.Kind.I8,
        KotlinKmpCAbiType.Scalar.Kind.BOOL,
        -> "readByte" to "writeByte"
        KotlinKmpCAbiType.Scalar.Kind.I16,
        KotlinKmpCAbiType.Scalar.Kind.CHAR16,
        -> "readShort" to "writeShort"
        KotlinKmpCAbiType.Scalar.Kind.I32 -> "readInt" to "writeInt"
        KotlinKmpCAbiType.Scalar.Kind.I64 -> "readLong" to "writeLong"
        KotlinKmpCAbiType.Scalar.Kind.F32 -> "readFloat" to "writeFloat"
        KotlinKmpCAbiType.Scalar.Kind.F64 -> "readDouble" to "writeDouble"
    }

internal fun enumMemoryPrimitives(scalar: KotlinKmpCAbiType.Scalar): Triple<String, String, String> =
    when (scalar.kind) {
        KotlinKmpCAbiType.Scalar.Kind.I8 ->
            if (scalar.unsigned) Triple("readUByte", "writeUByte", "value.toUByte()")
            else Triple("readByte", "writeByte", "value")
        KotlinKmpCAbiType.Scalar.Kind.I16 ->
            if (scalar.unsigned) Triple("readUShort", "writeUShort", "value.toUShort()")
            else Triple("readShort", "writeShort", "value")
        KotlinKmpCAbiType.Scalar.Kind.I32 ->
            if (scalar.unsigned) Triple("readUInt", "writeUInt", "value.toUInt()")
            else Triple("readInt", "writeInt", "value")
        KotlinKmpCAbiType.Scalar.Kind.I64 ->
            if (scalar.unsigned) Triple("readULong", "writeULong", "value.toULong()")
            else Triple("readLong", "writeLong", "value")
        else -> error("Unsupported enum carrier ${scalar.kind}")
    }

internal fun isStructType(type: Type): Boolean = when {
    type is Type.Declared -> {
        val tree = type.tree()
        (tree.kind() == Declaration.Scoped.Kind.STRUCT || tree.kind() == Declaration.Scoped.Kind.UNION) &&
            tree.members().filterIsInstance<Declaration.Variable>().isNotEmpty() &&
            !ClangUnnamedRecord.isPresent(tree)
    }
    type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> {
        val inner = type.type()
        isStructType(inner)
    }
    else -> false
}

internal fun canonicalRecordDeclaration(type: Type): Declaration.Scoped? = when (type) {
    is Type.Declared -> type.tree().takeIf { record ->
        record.kind() == Declaration.Scoped.Kind.STRUCT || record.kind() == Declaration.Scoped.Kind.UNION
    }
    is Type.Delegated -> canonicalRecordDeclaration(type.type())
    is Type.Array -> canonicalRecordDeclaration(type.elementType())
    else -> null
}

/** The union members of an inline (anonymous) union inside a record, when present. */
internal fun nativeDisplayUnionFields(
    typeMapper: KmpTypeMapper,
    decl: Declaration.Scoped,
): List<Declaration.Variable> =
    decl.members()
        .filterIsInstance<Declaration.Variable>()
        .filterNot(Skip::isPresent)
        .firstOrNull { typeMapper.declaredUnion(it.type()) != null }
        ?.type()
        ?.let(typeMapper::declaredUnion)
        ?.members()
        ?.filterIsInstance<Declaration.Variable>()
        ?: decl.members()
            .filterIsInstance<Declaration.Scoped>()
            .firstOrNull { it.kind() == Declaration.Scoped.Kind.UNION }
            ?.members()
            ?.filterIsInstance<Declaration.Variable>()
        ?: emptyList()

/**
 * Emits the getter/setter pair for one struct field over the enclosing
 * implementation's `private val buffer: MemoryBuffer`. [offsetBytes] and
 * [sizeBytes] come from the target record layout plan (Clang offsets).
 */
internal fun emitMemoryFieldAccessors(
    builder: SourceBuilder,
    typeMapper: KmpTypeMapper,
    abiIndex: KotlinKmpAbiIndex,
    nativeAddress: String,
    cString: String,
    memoryBuffer: String,
    field: Declaration.Variable,
    propertyName: String,
    fieldType: String,
    offsetBytes: Long,
    sizeBytes: Long,
) {
    val offset = offsetBytes
    when {
        fieldType == cString -> {
            builder.appendLine("get() = buffer.readPointer(${offset}uL).takeIf { it.rawValue != 0L }?.let(::$cString)")
            builder.appendLine("set(value) { buffer.writePointer(value?.handler ?: $nativeAddress(0L), ${offset}uL) }")
        }
        fieldType == nativeAddress -> {
            builder.appendLine("get() = buffer.readPointer(${offset}uL)")
            builder.appendLine("set(value) { buffer.writePointer(value, ${offset}uL) }")
        }
        fieldType == "$nativeAddress?" -> {
            builder.appendLine("get() = buffer.readPointer(${offset}uL).takeIf { it.rawValue != 0L }")
            builder.appendLine("set(value) { buffer.writePointer(value ?: $nativeAddress(0L), ${offset}uL) }")
        }
        typeMapper.isOptionsEnumType(field.type()) -> {
            val scalar = abiIndex.enum(typeMapper.enumDeclaration(field.type()))
            val (read, write) = memoryPrimitives(scalar)
            builder.appendLine("get() = $fieldType(${scalar.jvmCarrierToOptionsRaw("buffer.$read(${offset}uL)")})")
            builder.appendLine("set(value) { buffer.$write(${scalar.optionsRawToJvmCarrier("value.rawValue")}, ${offset}uL) }")
        }
        typeMapper.isEnumType(field.type()) -> {
            val scalar = abiIndex.enum(typeMapper.enumDeclaration(field.type()))
            val (read, write, cast) = enumMemoryPrimitives(scalar)
            builder.appendLine("get() = buffer.$read(${offset}uL) as $fieldType")
            builder.appendLine("set(value) { buffer.$write($cast, ${offset}uL) }")
        }
        isStructType(field.type()) -> {
            val fieldSize = sizeBytes
            builder.appendLine("get() = $fieldType.ByValue($nativeAddress(handle.rawValue + ${offset}L))")
            builder.appendLine("set(value) {")
            builder.indent()
            builder.appendLine("val bytes = ByteArray($fieldSize)")
            builder.appendLine("$memoryBuffer(value.handler, ${fieldSize}uL).readBytes(bytes, 0u, 0uL, ${fieldSize}uL)")
            builder.appendLine("buffer.writeBytes(bytes, 0u, ${offset}uL, ${fieldSize}uL)")
            builder.unindent()
            builder.appendLine("}")
        }
        fieldType == "Boolean" -> {
            check(sizeBytes == carrierBytesFor(fieldType)) {
                "field ${field.name()}: C size $sizeBytes != carrier ${carrierBytesFor(fieldType)}"
            }
            builder.appendLine("get() = buffer.readByte(${offset}uL) != 0.toByte()")
            builder.appendLine("set(value) { buffer.writeByte(if (value) 1 else 0, ${offset}uL) }")
        }
        fieldType in memoryScalarPrimitives -> {
            val (read, write) = memoryPrimitives(fieldType)
            val carrierBytes = carrierBytesFor(fieldType)
            check(sizeBytes == carrierBytes) {
                "field ${field.name()}: C size $sizeBytes != carrier $carrierBytes"
            }
            builder.appendLine("get() = buffer.$read(${offset}uL)")
            builder.appendLine("set(value) { buffer.$write(value, ${offset}uL) }")
        }
        fieldType.endsWith("?") -> {
            val nonOpt = fieldType.removeSuffix("?")
            builder.appendLine("get() = buffer.readPointer(${offset}uL).takeIf { it.rawValue != 0L }?.let { $nonOpt(it) }")
            builder.appendLine("set(value) { buffer.writePointer(value?.handler ?: $nativeAddress(0L), ${offset}uL) }")
        }
        else -> {
            builder.appendLine("get() = buffer.readPointer(${offset}uL).takeIf { it.rawValue != 0L }?.let { $fieldType(it) } ?: error(\"$propertyName is null\")")
            builder.appendLine("set(value) { buffer.writePointer(value.handler, ${offset}uL) }")
        }
    }
}
