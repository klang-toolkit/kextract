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

    fun invocation(receiver: String, selector: String, arguments: String): String {
        val raw = when {
            isVoid -> "ObjCRuntime.msgSend(null, $receiver, $selector$arguments)"
            returnsStructByValue ->
                "ObjCRuntime.msgSendStret($layout, $receiver, $selector$arguments)"
            else -> "ObjCRuntime.msgSend($layout, $receiver, $selector$arguments)"
        }
        return if (isVoid) raw else returnReconstruction(raw)
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
            val name = toplevel.javaName(pointedStruct.publicName)
            val pointerType = "${name}Pointer"
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
            return ObjCTypeLowering(
                kotlinType = "Boolean",
                layout = toplevel.layoutString(type),
                argumentLowering = { "if ($it) 1.toByte() else 0.toByte()" },
                returnReconstruction = { "($it as Byte) != 0.toByte()" },
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
