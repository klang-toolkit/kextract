package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Position
import org.graphiks.kextract.Type
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DuplicateFilterTest {
    private val intType = Type.primitive(Type.Primitive.Kind.Int)

    @Test
    fun `legacy enum-typed macro homonymous with an enum member survives`() {
        val enumMember = constant("KxSharedName", 1)
        val enumDecl = Declaration.enum_(Position.NO_POSITION, "KxType", enumMember)
        val macro = constant("KxSharedName", 2, Type.declared(enumDecl))
        val header = Declaration.toplevel(
            Position.NO_POSITION,
            enumDecl,
            macro,
        )

        DuplicateFilter().scan(header)

        assertFalse(isSkipped(enumMember))
        assertFalse(isSkipped(macro))
    }

    @Test
    fun `legacy enum-typed non-numeric macro homonym is suppressed`() {
        val enumMember = constant("KxNonNumericShared", 1)
        val enumDecl = Declaration.enum_(Position.NO_POSITION, "KxNonNumericType", enumMember)
        val macro = constant("KxNonNumericShared", 2.0f, Type.declared(enumDecl))

        DuplicateFilter().scan(
            Declaration.toplevel(Position.NO_POSITION, enumDecl, macro),
        )

        assertFalse(isSkipped(enumMember))
        assertTrue(isSkipped(macro))
    }

    @Test
    fun `legacy primitive macro homonymous with an enum member is suppressed`() {
        val enumMember = constant("KxPrimitiveShared", 1)
        val macro = constant("KxPrimitiveShared", 2)
        val header = Declaration.toplevel(
            Position.NO_POSITION,
            Declaration.enum_(Position.NO_POSITION, "KxPrimitiveType", enumMember),
            macro,
        )

        DuplicateFilter().scan(header)

        assertFalse(isSkipped(enumMember))
        assertTrue(isSkipped(macro))
    }

    @Test
    fun `legacy enum macro unwraps typedef and qualifier wrappers`() {
        val enumMember = constant("KxWrappedShared", 1)
        val enumDecl = Declaration.enum_(Position.NO_POSITION, "KxWrappedType", enumMember)
        val wrappedEnumType = Type.typedef(
            "KxWrappedAlias",
            Type.qualified(Type.Delegated.Kind.VOLATILE, Type.declared(enumDecl)),
        )
        val macro = constant("KxWrappedShared", 2, wrappedEnumType)

        DuplicateFilter().scan(
            Declaration.toplevel(Position.NO_POSITION, enumDecl, macro),
        )

        assertFalse(isSkipped(macro))
    }

    @Test
    fun `legacy pointer-to-enum macro homonymous with an enum member is suppressed`() {
        val enumMember = constant("KxPointerShared", 1)
        val enumDecl = Declaration.enum_(Position.NO_POSITION, "KxPointerType", enumMember)
        val pointerType = Type.typedef(
            "KxPointerAlias",
            Type.pointer(Type.declared(enumDecl)),
        )
        val macro = constant("KxPointerShared", 2, pointerType)

        DuplicateFilter().scan(
            Declaration.toplevel(Position.NO_POSITION, enumDecl, macro),
        )

        assertTrue(isSkipped(macro))
    }

    @Test
    fun `multiplatform enum-typed macro homonymous with an enum member is suppressed`() {
        val enumMember = constant("KxKmpShared", 1)
        val enumDecl = Declaration.enum_(Position.NO_POSITION, "KxKmpType", enumMember)
        val macro = constant("KxKmpShared", 2, Type.declared(enumDecl))

        DuplicateFilter(multiplatform = true).scan(
            Declaration.toplevel(Position.NO_POSITION, enumDecl, macro),
        )

        assertFalse(isSkipped(enumMember))
        assertTrue(isSkipped(macro))
    }

    @Test
    fun `duplicate top-level constants are still suppressed`() {
        val first = constant("KxRepeatedMacro", 1)
        val duplicate = constant("KxRepeatedMacro", 2)

        DuplicateFilter().scan(Declaration.toplevel(Position.NO_POSITION, first, duplicate))

        assertFalse(isSkipped(first))
        assertTrue(isSkipped(duplicate))
    }

    @Test
    fun `duplicate enum members are still suppressed`() {
        val first = constant("KxRepeatedMember", 1)
        val duplicate = constant("KxRepeatedMember", 2)
        val enumDecl = Declaration.enum_(Position.NO_POSITION, "KxType", first, duplicate)

        DuplicateFilter().scan(Declaration.toplevel(Position.NO_POSITION, enumDecl))

        assertFalse(isSkipped(first))
        assertTrue(isSkipped(duplicate))
    }

    @Test
    fun `availability metadata does not change a declaration hash identity`() {
        val declaration = constant("KxAvailable", 1)
        val declarations = hashSetOf<Declaration>(declaration)

        declaration.addAttribute(
            Declaration.PlatformAvailability(
                listOf(
                    Declaration.PlatformAvailability.Entry(
                        platform = "macos",
                        introduced = Declaration.PlatformAvailability.Version(13, 0, 0),
                        deprecated = null,
                        deprecatedWithoutVersion = false,
                        obsoleted = null,
                        unavailable = false,
                        message = "",
                    ),
                ),
            ),
        )

        assertTrue(declarations.contains(declaration))
    }

    private fun constant(
        name: String,
        value: Any,
        type: Type = intType,
    ): Declaration.Constant =
        Declaration.constant(Position.NO_POSITION, name, value, type)

    private fun isSkipped(declaration: Declaration): Boolean =
        declaration.attributes().any {
            it.javaClass.name == "org.graphiks.kextract.DeclarationImpl\$Skip"
        }
}
