package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.Type.Delegated
import org.graphiks.kextract.Type.Primitive
import org.graphiks.kextract.clang.Cursor
import org.graphiks.kextract.clang.TypeKind

/**
 * This class turns a clang type into a kextract type. Unlike declarations, kextract types are not
 * de-duplicated, so relying on the identity of a kextract type is wrong. Since pointer types can
 * point back to declarations, we create special pointer types backed by a supplier which fetches
 * the correct declaration from the tree maker cache. This makes sure that situations with
 * mutually referring pointers are dealt with correctly (i.e. by breaking cycles).
 */
internal object TypeMaker {

    fun makeType(t: org.graphiks.kextract.clang.Type, treeMaker: TreeMaker): Type {
        return when (t.kind()) {
            TypeKind.Auto -> makeType(t.canonicalType(), treeMaker)
            TypeKind.Void -> Type.void_()
            TypeKind.Char_S,
            TypeKind.Char_U -> Type.primitive(Primitive.Kind.Char)
            TypeKind.Short -> Type.primitive(Primitive.Kind.Short)
            TypeKind.Int -> Type.primitive(Primitive.Kind.Int)
            TypeKind.Long -> Type.primitive(Primitive.Kind.Long)
            TypeKind.LongLong -> Type.primitive(Primitive.Kind.LongLong)
            TypeKind.SChar -> {
                val chType = Type.primitive(Primitive.Kind.Char)
                Type.qualified(Delegated.Kind.SIGNED, chType)
            }
            TypeKind.UShort -> {
                val chType = Type.primitive(Primitive.Kind.Short)
                Type.qualified(Delegated.Kind.UNSIGNED, chType)
            }
            TypeKind.UInt -> {
                val chType = Type.primitive(Primitive.Kind.Int)
                Type.qualified(Delegated.Kind.UNSIGNED, chType)
            }
            TypeKind.ULong -> {
                val chType = Type.primitive(Primitive.Kind.Long)
                Type.qualified(Delegated.Kind.UNSIGNED, chType)
            }
            TypeKind.ULongLong -> {
                val chType = Type.primitive(Primitive.Kind.LongLong)
                Type.qualified(Delegated.Kind.UNSIGNED, chType)
            }
            TypeKind.UChar -> {
                val chType = Type.primitive(Primitive.Kind.Char)
                Type.qualified(Delegated.Kind.UNSIGNED, chType)
            }
            TypeKind.Bool -> Type.primitive(Primitive.Kind.Bool)
            TypeKind.Double -> Type.primitive(Primitive.Kind.Double)
            TypeKind.Float -> Type.primitive(Primitive.Kind.Float)
            TypeKind.Unexposed,
            TypeKind.Elaborated,
            TypeKind.PredefinedSugar -> {
                val canonical = t.canonicalType()
                if (canonical.equalType(t)) {
                    Type.error(t.spelling())
                } else {
                    makeType(canonical, treeMaker)
                }
            }
            TypeKind.ConstantArray -> {
                val elem = makeType(t.getElementType(), treeMaker)
                Type.array(t.getNumberOfElements(), elem)
            }
            TypeKind.IncompleteArray -> {
                val elem = makeType(t.getElementType(), treeMaker)
                Type.array(elem)
            }
            TypeKind.FunctionProto,
            TypeKind.FunctionNoProto -> {
                val args = mutableListOf<Type>()
                for (i in 0 until t.numberOfArgs()) {
                    args.add(lowerFunctionType(t.argType(i), treeMaker))
                }
                Type.function(t.isVariadic(), lowerFunctionType(t.resultType(), treeMaker), *args.toTypedArray())
            }
            TypeKind.Enum -> {
                val declaration = t.getDeclarationCursor()
                val definition = declaration.getDefinition()
                val d = treeMaker.createTree(if (definition.isInvalid()) declaration else definition)
                if (d != null) Type.declared(d as Declaration.Scoped) else Type.error(t.spelling())
            }
            TypeKind.Record -> {
                val declaration = t.getDeclarationCursor()
                val definition = declaration.getDefinition()
                val d = treeMaker.createTree(if (definition.isInvalid()) declaration else definition)
                if (d != null) Type.declared(d as Declaration.Scoped) else Type.error(t.spelling())
            }
            TypeKind.BlockPointer -> {
                // ObjC block type (e.g. `void (^completion)(NSError *)`).
                // Blocks are opaque callable objects; map to void* → MemorySegment on the Kotlin side.
                Type.pointer(Type.void_())
            }
            TypeKind.Pointer -> {
                val pointee = t.getPointeeType()
                if (pointee.kind() == TypeKind.FunctionProto ||
                    pointee.getDeclarationCursor().isInvalid()) {
                    Type.pointer(makeType(t.getPointeeType(), treeMaker))
                } else {
                    // struct/union pointer - defer processing of pointee type
                    val declCursor: Cursor = pointee.getDeclarationCursor()
                    val key: Cursor.Key = declCursor.toKey()
                    Type.pointer {
                        val decl = treeMaker.lookup(key)
                        if (decl == null) {
                            // no declaration, maybe an opaque type, give up and downgrade to void pointer
                            Type.void_()
                        } else {
                            when (val d = decl) {
                                is Declaration.Scoped -> Type.declared(d)
                                is Declaration.Typedef -> Type.typedef(d.name(), d.type())
                                else -> throw UnsupportedOperationException()
                            }
                        }
                    }
                }
            }
            TypeKind.Typedef -> {
                if (t.spelling() == "WCHAR") {
                    Type.typedef("WCHAR", Type.primitive(Primitive.Kind.WChar))
                } else {
                    val type = makeType(t.canonicalType(), treeMaker)
                    Type.typedef(t.spelling(), type)
                }
            }
            TypeKind.Complex -> {
                val type = makeType(t.getElementType(), treeMaker)
                Type.qualified(Delegated.Kind.COMPLEX, type)
            }
            TypeKind.Vector -> {
                val type = makeType(t.getElementType(), treeMaker)
                Type.vector(t.getNumberOfElements(), type)
            }
            TypeKind.WChar -> Type.primitive(Primitive.Kind.WChar)      // unsupported
            TypeKind.Char16 -> Type.primitive(Primitive.Kind.Char16)    // unsupported
            TypeKind.Half -> Type.primitive(Primitive.Kind.HalfFloat)   // unsupported
            TypeKind.Int128 -> Type.primitive(Primitive.Kind.Int128)    // unsupported
            TypeKind.LongDouble -> Type.primitive(Primitive.Kind.LongDouble) // unsupported
            TypeKind.UInt128 -> {                                        // unsupported
                val iType = Type.primitive(Primitive.Kind.Int128)
                Type.qualified(Delegated.Kind.UNSIGNED, iType)
            }
            TypeKind.Atomic -> {
                val aType = makeType(t.getValueType(), treeMaker)
                Type.qualified(Delegated.Kind.ATOMIC, aType)
            }
            // Objective-C types — all map to opaque pointer (MemorySegment in Kotlin).
            // For ObjCObjectPointer we preserve the interface name as a typedef wrapper so that
            // post-processing code (e.g. NSString convenience-overload detection) can identify
            // the concrete ObjC class.  TypeMapper still maps any typedef-over-pointer to
            // MemorySegment, so the Kotlin surface is unchanged.
            // Example: `NSString *` → Type.typedef("NSString", Type.pointer(void))
            TypeKind.ObjCId,
            TypeKind.ObjCClass,
            TypeKind.ObjCSel -> Type.pointer(Type.void_())
            TypeKind.ObjCObjectPointer -> {
                // t.spelling() is e.g. "NSString *" or "NSArray<NSString *> *" or "id"
                val spelling = t.spelling()
                val className = spelling.removeSuffix(" *").trim()
                // Only wrap simple (non-generic, non-id) interface names
                if (className.isNotEmpty() && !className.contains(" ") && !className.contains("<") && className != "id") {
                    Type.typedef(className, Type.pointer(Type.void_()))
                } else {
                    Type.pointer(Type.void_())
                }
            }
            TypeKind.ObjCInterface,
            TypeKind.ObjCObject,
            TypeKind.ObjCTypeParam -> Type.pointer(Type.void_())
            else -> Type.error(t.spelling())
        }
    }

    private fun lowerFunctionType(t: org.graphiks.kextract.clang.Type, treeMaker: TreeMaker): Type {
        val t2 = makeType(t, treeMaker)
        return t2.accept(lowerFunctionTypeVisitor)
    }

    private val lowerFunctionTypeVisitor = object : Type.Visitor<Type> {
        override fun visitArray(t: Type.Array): Type {
            return Type.pointer(t.elementType())
        }

        override fun visitDelegated(t: Type.Delegated): Type {
            if (t.kind() == Delegated.Kind.TYPEDEF && t.type() is Type.Array) {
                return visitArray(t.type() as Type.Array)
            }
            return visitType(t)
        }

        override fun visitType(t: Type): Type {
            return t
        }
    }
}
