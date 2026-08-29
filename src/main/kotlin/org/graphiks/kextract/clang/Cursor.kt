package org.graphiks.kextract.clang

import java.lang.foreign.Arena
import java.lang.foreign.GroupLayout
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.ValueLayout
import java.lang.foreign.MemoryLayout.PathElement.groupElement
import java.util.Objects
import java.util.function.Consumer
import java.util.function.Predicate
import org.graphiks.kextract.clang.libclang.*

class Cursor internal constructor(segment: MemorySegment, owner: ClangDisposable) :
    ClangDisposable.Owned(segment, owner) {

    private val kind: Int = clang_getCursorKind(segment)

    /** Returns this cursor's kind, or null if the kind integer is not in the [CursorKind] enum. */
    fun kindOrNull(): CursorKind? = CursorKind.valueOfOrNull(kind)

    fun isDeclaration(): Boolean      = clang_isDeclaration(kind) != 0
    fun isPreprocessing(): Boolean     = clang_isPreprocessing(kind) != 0
    fun isInvalid(): Boolean           = clang_isInvalid(kind) != 0
    fun isDefinition(): Boolean        = clang_isCursorDefinition(segment) != 0
    fun isAttribute(): Boolean         = clang_isAttribute(kind) != 0
    fun isAnonymousStruct(): Boolean   = clang_Cursor_isAnonymousRecordDecl(segment) != 0
    fun isAnonymous(): Boolean         = clang_Cursor_isAnonymous(segment) != 0
    fun isMacroFunctionLike(): Boolean = clang_Cursor_isMacroFunctionLike(segment) != 0

    fun spelling(): String {
        val s = clang_getCursorSpelling(LibClang.STRING_ALLOCATOR.get(), segment)
        return LibClang.CXStrToString(s)
    }

    fun USR(): String {
        val u = clang_getCursorUSR(LibClang.STRING_ALLOCATOR.get(), segment)
        return LibClang.CXStrToString(u)
    }

    fun prettyPrinted(policy: PrintingPolicy): String {
        val s = clang_getCursorPrettyPrinted(LibClang.STRING_ALLOCATOR.get(), segment, policy.ptr())
        return LibClang.CXStrToString(s)
    }

    fun prettyPrinted(): String = getPrintingPolicy().use { prettyPrinted(it) }

    fun displayName(): String {
        val s = clang_getCursorDisplayName(LibClang.STRING_ALLOCATOR.get(), segment)
        return LibClang.CXStrToString(s)
    }

    fun rawCommentText(): String {
        val s = clang_Cursor_getRawCommentText(LibClang.STRING_ALLOCATOR.get(), segment)
        return LibClang.CXStrToString(s)
    }

    fun briefCommentText(): String {
        val s = clang_Cursor_getBriefCommentText(LibClang.STRING_ALLOCATOR.get(), segment)
        return LibClang.CXStrToString(s)
    }

    fun equalCursor(other: Cursor): Boolean = clang_equalCursors(segment, other.segment) != 0

    fun type(): Type               = Type(clang_getCursorType(owner, segment), owner)
    /** Return type of a function or ObjC method cursor (uses clang_getCursorResultType, not clang_getCursorType). */
    fun resultType(): Type         = Type(clang_getCursorResultType(owner, segment), owner)
    fun getEnumDeclIntegerType(): Type = Type(clang_getEnumDeclIntegerType(owner, segment), owner)
    fun getDefinition(): Cursor    = Cursor(clang_getCursorDefinition(owner, segment), owner)
    fun getVarDeclInitializer(): Cursor = Cursor(clang_Cursor_getVarDeclInitializer(owner, segment), owner)
    fun isFunctionInlined(): Boolean = clang_Cursor_isFunctionInlined(segment) != 0

    fun platformAvailability(): List<org.graphiks.kextract.Declaration.PlatformAvailability.Entry> =
        Arena.ofConfined().use { arena ->
            val count = clang_getCursorPlatformAvailability(
                segment,
                MemorySegment.NULL,
                MemorySegment.NULL,
                MemorySegment.NULL,
                MemorySegment.NULL,
                MemorySegment.NULL,
                0,
            )
            val alwaysDeprecated = arena.allocate(ValueLayout.JAVA_INT)
            val deprecatedMessage = CXString.allocate(arena)
            val alwaysUnavailable = arena.allocate(ValueLayout.JAVA_INT)
            val unavailableMessage = CXString.allocate(arena)
            val entries = if (count > 0) {
                arena.allocate(MemoryLayout.sequenceLayout(count.toLong(), PlatformAvailabilityLayout.layout))
            } else {
                MemorySegment.NULL
            }
            clang_getCursorPlatformAvailability(
                segment,
                alwaysDeprecated,
                deprecatedMessage,
                alwaysUnavailable,
                unavailableMessage,
                entries,
                count,
            )

            buildList {
                for (index in 0 until count) {
                    val entry = entries.asSlice(index * PlatformAvailabilityLayout.byteSize, PlatformAvailabilityLayout.byteSize)
                    try {
                        add(
                            org.graphiks.kextract.Declaration.PlatformAvailability.Entry(
                                platform = borrowedCXString(PlatformAvailabilityLayout.platform(entry)),
                                introduced = PlatformAvailabilityLayout.introduced(entry).toVersion(),
                                deprecated = PlatformAvailabilityLayout.deprecated(entry).toVersion(),
                                deprecatedWithoutVersion = false,
                                obsoleted = PlatformAvailabilityLayout.obsoleted(entry).toVersion(),
                                unavailable = PlatformAvailabilityLayout.unavailable(entry) != 0,
                                message = borrowedCXString(PlatformAvailabilityLayout.message(entry)),
                            ),
                        )
                    } finally {
                        clang_disposeCXPlatformAvailability(entry)
                    }
                }
                val deprecated = alwaysDeprecated.get(ValueLayout.JAVA_INT, 0) != 0
                val unavailable = alwaysUnavailable.get(ValueLayout.JAVA_INT, 0) != 0
                if (deprecated || unavailable) {
                    add(
                        org.graphiks.kextract.Declaration.PlatformAvailability.Entry(
                            platform = "all",
                            introduced = null,
                            deprecated = null,
                            deprecatedWithoutVersion = deprecated,
                            obsoleted = null,
                            unavailable = unavailable,
                            message = if (unavailable) LibClang.CXStrToString(unavailableMessage) else LibClang.CXStrToString(deprecatedMessage),
                        ),
                    )
                } else {
                    LibClang.CXStrToString(deprecatedMessage)
                    LibClang.CXStrToString(unavailableMessage)
                }
            }
        }

    /**
     * `CXPlatformAvailability` is a libclang-owned output structure.  Keep the
     * layout beside its consumer so this wrapper stays usable while Kextract
     * bootstraps its own generated libclang bindings.
     */
    private object PlatformAvailabilityLayout {
        val layout: GroupLayout = MemoryLayout.structLayout(
            CXString.layout.withName("Platform"),
            CXVersion.layout.withName("Introduced"),
            CXVersion.layout.withName("Deprecated"),
            CXVersion.layout.withName("Obsoleted"),
            ValueLayout.JAVA_INT.withName("Unavailable"),
            CXString.layout.withName("Message"),
        ).withName("CXPlatformAvailability")

        val byteSize: Long = layout.byteSize()

        fun platform(entry: MemorySegment): MemorySegment = field(entry, "Platform")
        fun introduced(entry: MemorySegment): MemorySegment = field(entry, "Introduced")
        fun deprecated(entry: MemorySegment): MemorySegment = field(entry, "Deprecated")
        fun obsoleted(entry: MemorySegment): MemorySegment = field(entry, "Obsoleted")
        fun message(entry: MemorySegment): MemorySegment = field(entry, "Message")

        fun unavailable(entry: MemorySegment): Int =
            entry.get(ValueLayout.JAVA_INT, layout.byteOffset(groupElement("Unavailable")))

        private fun field(entry: MemorySegment, name: String): MemorySegment =
            entry.asSlice(layout.byteOffset(groupElement(name)), layout.select(groupElement(name)).byteSize())
    }

    private fun borrowedCXString(value: MemorySegment): String {
        val chars = clang_getCString(value)
        return if (chars == MemorySegment.NULL) "" else chars.reinterpret(Long.MAX_VALUE).getString(0)
    }

    private fun MemorySegment.toVersion(): org.graphiks.kextract.Declaration.PlatformAvailability.Version? {
        val major = CXVersion.Major(this)
        return if (major < 0) null else org.graphiks.kextract.Declaration.PlatformAvailability.Version(
            major,
            CXVersion.Minor(this),
            CXVersion.Subminor(this),
        )
    }

    fun getSourceLocation(): SourceLocation? {
        val loc = clang_getCursorLocation(owner, segment)
        Arena.ofConfined().use { arena ->
            if (clang_equalLocations(loc, clang_getNullLocation(arena)) != 0) return null
        }
        return SourceLocation(loc, owner)
    }

    fun getExtent(): SourceRange? {
        val range = clang_getCursorExtent(owner, segment)
        if (clang_Range_isNull(range) != 0) return null
        return SourceRange(range, owner)
    }

    fun numberOfArgs(): Int          = clang_Cursor_getNumArguments(segment)
    fun getArgument(idx: Int): Cursor = Cursor(clang_Cursor_getArgument(owner, segment, idx), owner)

    fun getEnumConstantValue(): Long         = clang_getEnumConstantDeclValue(segment)
    fun getEnumConstantUnsignedValue(): Long  = clang_getEnumConstantDeclUnsignedValue(segment)
    fun isBitField(): Boolean                = clang_Cursor_isBitField(segment) != 0
    fun getBitFieldWidth(): Int              = clang_getFieldDeclBitWidth(segment)

    // ObjC-specific accessors
    /** Bitmask of CXObjCPropertyAttr_* values (readonly=1, readwrite=8, etc.) */
    fun getObjCPropertyAttributes(): Int     = clang_Cursor_getObjCPropertyAttributes(segment, 0)
    /** Custom getter selector name, or empty string if not specified. */
    fun getObjCPropertyGetterName(): String {
        val s = clang_Cursor_getObjCPropertyGetterName(LibClang.STRING_ALLOCATOR.get(), segment)
        return LibClang.CXStrToString(s)
    }
    /** Custom setter selector name, or empty string if not specified. */
    fun getObjCPropertySetterName(): String {
        val s = clang_Cursor_getObjCPropertySetterName(LibClang.STRING_ALLOCATOR.get(), segment)
        return LibClang.CXStrToString(s)
    }
    /** Returns true for @optional protocol methods. */
    fun isObjCOptional(): Boolean            = clang_Cursor_isObjCOptional(segment) != 0

    fun kind(): CursorKind      = CursorKind.valueOf(kind)
    fun language(): CursorLanguage = CursorLanguage.valueOf(clang_getCursorLanguage(segment))
    fun linkage(): LinkageKind  = LinkageKind.valueOf(clang_getCursorLinkage(segment))
    fun kind0(): Int            = kind

    fun getCursorReferenced(): Cursor =
        Cursor(clang_getCursorReferenced(owner, segment), owner)

    fun forEach(action: Consumer<Cursor>) {
        CursorChildren.forEach(this, action)
    }

    fun forEachShortCircuit(action: Predicate<Cursor>) {
        CursorChildren.forEachShortCircuit(this, action)
    }

    private object CursorChildren {

        class Context(val action: Predicate<Cursor>, val owner: ClangDisposable) {
            var exception: RuntimeException? = null

            fun visit(segment: MemorySegment): Boolean {
                return try {
                    action.test(Cursor(segment, owner))
                } catch (ex: RuntimeException) {
                    exception = ex
                    false
                }
            }

            fun handleExceptions() { exception?.let { throw it } }
        }

        var pendingContext: Context? = null

        private val callback: MemorySegment = CXCursorVisitor.allocate({ c, _, _ ->
            if (pendingContext!!.visit(c)) CXChildVisit_Continue()
            else CXChildVisit_Break()
        }, Arena.global())

        fun forEach(c: Cursor, op: Consumer<Cursor>) {
            forEachShortCircuit(c) { decl -> op.accept(decl); true }
        }

        @Synchronized
        fun forEachShortCircuit(c: Cursor, op: Predicate<Cursor>) {
            val prevContext = pendingContext
            try {
                pendingContext = Context(op, c.owner)
                clang_visitChildren(c.segment, callback, MemorySegment.NULL)
                pendingContext!!.handleExceptions()
            } finally {
                pendingContext = prevContext
            }
        }
    }

    fun getTranslationUnit(): TranslationUnit =
        TranslationUnit(clang_Cursor_getTranslationUnit(segment))

    fun eval(): EvalResult {
        val ptr = clang_Cursor_Evaluate(segment)
        return if (ptr == MemorySegment.NULL) EvalResult.erroneous else EvalResult(ptr)
    }

    fun getPrintingPolicy(): PrintingPolicy =
        PrintingPolicy(clang_getCursorPrintingPolicy(segment))

    fun toKey(): Key = Key(this)

    class Key internal constructor(cursor: Cursor) {
        val spelling: String = cursor.spelling()
        val kind: CursorKind = cursor.kind()
        val payload: MemorySegment = MemorySegment.ofArray(ByteArray(CXCursor.byteSize.toInt())).also {
            it.copyFrom(cursor.segment)
        }

        override fun equals(other: Any?): Boolean {
            if (other !is Key) return false
            if (kind != other.kind) return false
            if (spelling != other.spelling) return false
            val allocator = SegmentAllocator.slicingAllocator(COMPARISON_SEGMENT)
            return clang_equalCursors(toSegment(allocator), other.toSegment(allocator)) != 0
        }

        override fun hashCode(): Int = Objects.hash(kind, spelling)

        private fun toSegment(allocator: SegmentAllocator): MemorySegment =
            allocator.allocateFrom(ValueLayout.JAVA_BYTE, payload, ValueLayout.JAVA_BYTE, 0, CXCursor.byteSize)

        companion object {
            private val COMPARISON_SEGMENT: MemorySegment = Arena.ofAuto().allocate(CXCursor.layout, 2)
        }
    }
}
