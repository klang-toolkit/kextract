package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.DeclarationImpl.ClangEnumType
import org.graphiks.kextract.Type
import org.graphiks.kextract.kotlin.utils.TypeMapper

/**
 * Complete lowering of one Objective-C surface type.
 *
 * The same descriptor owns the public Kotlin type, FFM layout, downcall argument
 * carrier, and return reconstruction so those four views cannot drift apart.
 */
internal data class ObjCTypeLowering(
    val kotlinType: String,
    val layout: String,
    val isVoid: Boolean = false,
    val returnsStructByValue: Boolean = false,
    private val argumentLowering: (String) -> String = { it },
    private val returnReconstruction: (String) -> String = { it },
) {
    fun lowerArgument(name: String): String = argumentLowering(name)
    fun reconstruct(rawValue: String): String = returnReconstruction(rawValue)

    fun invocation(receiver: String, selector: String, arguments: String): String {
        val raw = when {
            isVoid -> "ObjCRuntime.msgSend(null, $receiver, $selector$arguments)"
            returnsStructByValue ->
                "ObjCRuntime.msgSendStruct($layout, $receiver, $selector$arguments)"
            else -> "ObjCRuntime.msgSend($layout, $receiver, $selector$arguments)"
        }
        return if (isVoid) raw else reconstruct(raw)
    }
}

/** Builds [ObjCTypeLowering] descriptors without SDK-specific type allowlists. */
internal class ObjCTypeLowerer(private val toplevel: KotlinToplevelBuilder) {
    fun lower(type: Type): ObjCTypeLowering {
        if (isVoid(type)) {
            return ObjCTypeLowering("Unit", "null", isVoid = true)
        }

        val pointedStruct = TypeMapper.pointedStruct(type)
        if (pointedStruct != null) {
            val pointerType = toplevel.javaName(pointedStruct.publicName)
            return ObjCTypeLowering(
                kotlinType = pointerType,
                layout = "ValueLayout.ADDRESS",
                argumentLowering = { "$it.segment" },
                returnReconstruction = { "$pointerType($it as MemorySegment)" },
            )
        }

        val valueStruct = TypeMapper.namedStruct(type)
        if (valueStruct != null) {
            val name = toplevel.javaName(valueStruct.publicName)
            return ObjCTypeLowering(
                kotlinType = name,
                layout = "$name.layout",
                returnsStructByValue = true,
                argumentLowering = { "ObjCRuntime.ObjCStructArg($it.segment, $name.layout)" },
                returnReconstruction = { "$name($it)" },
            )
        }

        val enumDecl = toplevel.resolveObjCEnum(type)
        if (enumDecl != null) {
            val name = toplevel.javaName(enumDecl.name())
            val underlying = ClangEnumType.get(enumDecl) ?: Type.primitive(Type.Primitive.Kind.Int)
            val carrier = TypeMapper.map(underlying)
            return ObjCTypeLowering(
                kotlinType = name,
                layout = toplevel.layoutString(underlying),
                argumentLowering = { rawValueToCarrier("$it.rawValue", carrier) },
                returnReconstruction = { raw ->
                    "$name(${carrierToLong("$raw as $carrier", carrier, isUnsigned(underlying))})"
                },
            )
        }

        if (isBoolTypedef(type)) {
            val carrier = TypeMapper.map(type)
            return ObjCTypeLowering(
                kotlinType = "Boolean",
                layout = toplevel.layoutString(type),
                argumentLowering = { boolToCarrier(it, carrier) },
                returnReconstruction = { carrierToBool(it, carrier) },
            )
        }

        val kotlinType = TypeMapper.map(type)
        return ObjCTypeLowering(
            kotlinType = kotlinType,
            layout = toplevel.layoutString(type),
            returnReconstruction = { "$it as $kotlinType" },
        )
    }

    private fun rawValueToCarrier(expression: String, carrier: String): String = when (carrier) {
        "Long" -> expression
        "Int" -> "$expression.toInt()"
        "Short" -> "$expression.toShort()"
        "Byte" -> "$expression.toByte()"
        else -> expression
    }

    private fun carrierToLong(expression: String, carrier: String, unsigned: Boolean): String = when {
        carrier == "Int" && unsigned -> "Integer.toUnsignedLong($expression)"
        carrier == "Short" && unsigned -> "java.lang.Short.toUnsignedInt($expression).toLong()"
        carrier == "Byte" && unsigned -> "java.lang.Byte.toUnsignedInt($expression).toLong()"
        carrier == "Long" -> expression
        carrier == "Int" || carrier == "Short" || carrier == "Byte" -> "($expression).toLong()"
        else -> expression
    }

    private fun boolToCarrier(expression: String, carrier: String): String = when (carrier) {
        "Boolean" -> expression
        "Byte" -> "if ($expression) 1.toByte() else 0.toByte()"
        "Short" -> "if ($expression) 1.toShort() else 0.toShort()"
        "Int" -> "if ($expression) 1 else 0"
        "Long" -> "if ($expression) 1L else 0L"
        else -> error("Unsupported BOOL carrier: $carrier")
    }

    private fun carrierToBool(expression: String, carrier: String): String = when (carrier) {
        "Boolean" -> "$expression as Boolean"
        "Byte" -> "($expression as Byte) != 0.toByte()"
        "Short" -> "($expression as Short) != 0.toShort()"
        "Int" -> "($expression as Int) != 0"
        "Long" -> "($expression as Long) != 0L"
        else -> error("Unsupported BOOL carrier: $carrier")
    }

    private fun isUnsigned(type: Type): Boolean = when {
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.UNSIGNED -> true
        type is Type.Delegated && type.kind() != Type.Delegated.Kind.POINTER -> isUnsigned(type.type())
        else -> false
    }

    private fun isVoid(type: Type): Boolean = when {
        type is Type.Primitive -> type.kind() == Type.Primitive.Kind.Void
        type is Type.Delegated && type.kind() != Type.Delegated.Kind.POINTER -> isVoid(type.type())
        else -> false
    }

    private fun isBoolTypedef(type: Type): Boolean = when {
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF ->
            type.name() == "BOOL" || isBoolTypedef(type.type())
        type is Type.Delegated && type.kind() != Type.Delegated.Kind.POINTER ->
            isBoolTypedef(type.type())
        else -> false
    }
}
