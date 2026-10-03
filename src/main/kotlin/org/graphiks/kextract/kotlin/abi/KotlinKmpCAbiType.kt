package org.graphiks.kextract.kotlin.abi

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.ClangEnumType
import org.graphiks.kextract.DeclarationImpl.JavaName
import org.graphiks.kextract.Type
import org.graphiks.kextract.pipeline.KextractConfig
import org.graphiks.kextract.pipeline.isEnum
import org.graphiks.kextract.pipeline.isStructOrUnion

internal enum class KotlinKmpAbiContext {
    CALLBACK,
    DIRECT,
    FIELD,
}

/** The normalized raw C ABI shape used by all multiplatform emitters. */
internal sealed interface KotlinKmpCAbiType {
    val jvmLayout: String
    val jvmCarrier: String

    data class Scalar(
        val kind: Kind,
        val unsigned: Boolean,
    ) : KotlinKmpCAbiType {
        enum class Kind(
            val jvmLayout: String,
            val jvmCarrier: String,
            val signedKotlinType: String,
            val unsignedKotlinType: String,
        ) {
            BOOL("ValueLayout.JAVA_BOOLEAN", "Boolean", "Boolean", "Boolean"),
            I8("ValueLayout.JAVA_BYTE", "Byte", "Byte", "UByte"),
            I16("ValueLayout.JAVA_SHORT", "Short", "Short", "UShort"),
            I32("ValueLayout.JAVA_INT", "Int", "Int", "UInt"),
            I64("ValueLayout.JAVA_LONG", "Long", "Long", "ULong"),
            CHAR16("ValueLayout.JAVA_CHAR", "Char", "Char", "UShort"),
            F32("ValueLayout.JAVA_FLOAT", "Float", "Float", "Float"),
            F64("ValueLayout.JAVA_DOUBLE", "Double", "Double", "Double"),
        }

        override val jvmLayout: String = kind.jvmLayout
        override val jvmCarrier: String = kind.jvmCarrier

        val kotlinType: String
            get() = if (unsigned) kind.unsignedKotlinType else kind.signedKotlinType

        val nativeCarrier: String
            get() = kotlinType

        fun fromJvmCarrier(expression: String): String = when {
            !unsigned -> expression
            kind == Kind.I8 -> "($expression).toUByte()"
            kind == Kind.I16 -> "($expression).toUShort()"
            kind == Kind.I32 -> "($expression).toUInt()"
            kind == Kind.I64 -> "($expression).toULong()"
            else -> expression
        }

        fun toJvmCarrier(expression: String): String = when {
            !unsigned -> expression
            kind == Kind.I8 -> "$expression.toByte()"
            kind == Kind.I16 -> "$expression.toShort()"
            kind == Kind.I32 -> "$expression.toInt()"
            kind == Kind.I64 -> "$expression.toLong()"
            else -> expression
        }

        fun optionsRawToJvmCarrier(expression: String): String = when (kind) {
            Kind.I8 -> "$expression.toByte()"
            Kind.I16 -> "$expression.toShort()"
            Kind.I32 -> "$expression.toInt()"
            Kind.I64 -> expression
            else -> expression
        }

        fun jvmCarrierToOptionsRaw(expression: String): String = when {
            kind == Kind.I64 -> expression
            unsigned && kind == Kind.I8 -> "($expression).toUByte().toLong()"
            unsigned && kind == Kind.I16 -> "($expression).toUShort().toLong()"
            unsigned && kind == Kind.I32 -> "($expression).toUInt().toLong()"
            else -> "($expression).toLong()"
        }

        fun optionsRawToKotlinScalar(expression: String): String = when {
            unsigned && kind == Kind.I8 -> "$expression.toUByte()"
            unsigned && kind == Kind.I16 -> "$expression.toUShort()"
            unsigned && kind == Kind.I32 -> "$expression.toUInt()"
            unsigned && kind == Kind.I64 -> "$expression.toULong()"
            kind == Kind.I8 -> "$expression.toByte()"
            kind == Kind.I16 -> "$expression.toShort()"
            kind == Kind.I32 -> "$expression.toInt()"
            kind == Kind.I64 -> expression
            else -> expression
        }

        fun kotlinScalarToOptionsRaw(expression: String): String = when {
            kind == Kind.I64 && !unsigned -> expression
            else -> "$expression.toLong()"
        }

        /** Renders Clang's sign-extended enum value at the declared carrier width. */
        fun enumConstantLiteral(value: Long): String = when {
            !unsigned && kind == Kind.I64 -> when (value) {
                Long.MIN_VALUE -> "-9223372036854775807L - 1L"
                else -> "${value}L"
            }
            !unsigned -> value.toString()
            kind == Kind.I8 -> "${value and 0xffL}u"
            kind == Kind.I16 -> "${value and 0xffffL}u"
            kind == Kind.I32 -> "${java.lang.Integer.toUnsignedString(value.toInt())}u"
            kind == Kind.I64 -> "${java.lang.Long.toUnsignedString(value)}uL"
            else -> value.toString()
        }

        fun enumConstantOptionsRawLiteral(value: Long): String {
            val rawValue = when {
                !unsigned -> value
                kind == Kind.I8 -> value and 0xffL
                kind == Kind.I16 -> value and 0xffffL
                kind == Kind.I32 -> java.lang.Integer.toUnsignedLong(value.toInt())
                kind == Kind.I64 -> value
                else -> value
            }
            return if (rawValue == Long.MIN_VALUE) "Long.MIN_VALUE" else "${rawValue}L"
        }
    }

    data class StructValue(
        val declaration: Declaration.Scoped,
    ) : KotlinKmpCAbiType {
        override val jvmLayout: String
            get() = "${JavaName.getFullNameOrThrow(declaration)}.layout"
        override val jvmCarrier: String = "MemorySegment"
    }

    data class Address(
        /** Number of C pointer indirections preserved through typedefs. */
        val pointerDepth: Int,
    ) : KotlinKmpCAbiType {
        override val jvmLayout: String = "ValueLayout.ADDRESS"
        override val jvmCarrier: String = "MemorySegment"
    }

    companion object {
        fun from(type: Type, context: KotlinKmpAbiContext): KotlinKmpCAbiType =
            normalize(type, context, unsigned = false)

        private fun normalize(
            type: Type,
            context: KotlinKmpAbiContext,
            unsigned: Boolean,
        ): KotlinKmpCAbiType = when {
            type.isErroneous() -> Address(pointerDepth = 1)
            type is Type.Primitive -> scalar(type.kind(), context, unsigned)
            type is Type.Declared && type.isEnum() -> {
                val underlying = requireNotNull(ClangEnumType.get(type.tree())) {
                    "Enum ${type.tree().name()} has no Clang underlying type"
                }
                normalize(underlying, context, unsigned = false)
            }
            type is Type.Declared && type.isStructOrUnion() -> StructValue(type.tree())
            type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER ->
                Address(pointerDepth = pointerDepth(type))
            type is Type.Delegated && type.kind() == Type.Delegated.Kind.UNSIGNED ->
                normalize(type.type(), context, unsigned = true)
            type is Type.Delegated && type.kind() == Type.Delegated.Kind.SIGNED ->
                normalize(type.type(), context, unsigned = false)
            type is Type.Delegated -> normalize(type.type(), context, unsigned)
            type is Type.Function || type is Type.Array -> Address(pointerDepth = 1)
            else -> throw UnsupportedOperationException("Unsupported ${context.label} C ABI type: $type")
        }

        private fun scalar(
            kind: Type.Primitive.Kind,
            context: KotlinKmpAbiContext,
            unsigned: Boolean,
        ): Scalar = when (kind) {
            Type.Primitive.Kind.Bool -> Scalar(Scalar.Kind.BOOL, unsigned = false)
            Type.Primitive.Kind.Char -> Scalar(Scalar.Kind.I8, unsigned)
            Type.Primitive.Kind.Char16 -> Scalar(Scalar.Kind.CHAR16, unsigned = true)
            Type.Primitive.Kind.Short -> Scalar(Scalar.Kind.I16, unsigned)
            Type.Primitive.Kind.Int -> Scalar(Scalar.Kind.I32, unsigned)
            Type.Primitive.Kind.Long ->
                if (KextractConfig.allow64BitScalars) {
                    Scalar(Scalar.Kind.I64, unsigned)
                } else {
                    unsupportedVariableWidthScalar(
                        context,
                        "long",
                        "target-dependent width (LP64 vs LLP64); use a fixed-width C integer type",
                    )
                }
            Type.Primitive.Kind.LongLong -> Scalar(Scalar.Kind.I64, unsigned)
            Type.Primitive.Kind.Float -> Scalar(Scalar.Kind.F32, unsigned = false)
            Type.Primitive.Kind.Double -> Scalar(Scalar.Kind.F64, unsigned = false)
            Type.Primitive.Kind.LongDouble -> unsupportedVariableWidthScalar(
                context,
                "long double",
                "target-dependent size and format; use double or an explicit fixed-width representation",
            )
            else -> unsupportedScalar(context, kind)
        }

        private fun unsupportedVariableWidthScalar(
            context: KotlinKmpAbiContext,
            name: String,
            reason: String,
        ): Nothing = throw UnsupportedOperationException(
            "Unsupported multiplatform ${context.label} C ABI scalar '$name': $reason",
        )

        private fun unsupportedScalar(
            context: KotlinKmpAbiContext,
            kind: Type.Primitive.Kind,
        ): Nothing = throw UnsupportedOperationException(
            "Unsupported ${context.label} C ABI scalar: $kind",
        )

        private fun pointerDepth(type: Type): Int = when {
            type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER ->
                1 + pointerDepth(type.type())
            type is Type.Delegated -> pointerDepth(type.type())
            else -> 0
        }

        private val KotlinKmpAbiContext.label: String
            get() = when (this) {
                KotlinKmpAbiContext.CALLBACK -> "callback"
                KotlinKmpAbiContext.DIRECT -> "direct"
                KotlinKmpAbiContext.FIELD -> "enum"
            }
    }
}
