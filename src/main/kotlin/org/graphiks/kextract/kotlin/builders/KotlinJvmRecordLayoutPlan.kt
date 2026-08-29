package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.ClangAlignOf
import org.graphiks.kextract.DeclarationImpl.ClangOffsetOf
import org.graphiks.kextract.DeclarationImpl.ClangSizeOf
import org.graphiks.kextract.DeclarationImpl.Skip
import java.util.IdentityHashMap

/**
 * Per-record Clang byte sizes, alignment, and field offsets for JVM emission.
 * Legacy FFM record layouts and memory-backed KMP structs both consume the
 * resulting [KotlinJvmRecordLayout].
 */
internal class KotlinJvmRecordLayoutPlan private constructor(
    private val layouts: IdentityHashMap<Declaration.Scoped, KotlinJvmRecordLayout>,
) {
    operator fun get(declaration: Declaration.Scoped): KotlinJvmRecordLayout =
        requireNotNull(layouts[declaration]) {
            "No JVM record layout was planned for ${declaration.name()}"
        }

    companion object {
        fun create(scoped: Declaration.Scoped): KotlinJvmRecordLayoutPlan {
            val layouts = IdentityHashMap<Declaration.Scoped, KotlinJvmRecordLayout>()

            fun collect(declaration: Declaration) {
                if (declaration !is Declaration.Scoped) return
                if (
                    !Skip.isPresent(declaration) &&
                    declaration.kind() in setOf(Declaration.Scoped.Kind.STRUCT, Declaration.Scoped.Kind.UNION)
                ) {
                    layouts[declaration] = createRecord(declaration)
                }
                declaration.members().forEach(::collect)
            }

            collect(scoped)
            return KotlinJvmRecordLayoutPlan(layouts)
        }

        internal fun createRecord(declaration: Declaration.Scoped): KotlinJvmRecordLayout {
            val owner = declaration.name()
            val sizeBytes = bitsToBytes(
                metric = "size",
                owner = owner,
                bits = requireNotNull(ClangSizeOf.get(declaration)) {
                    "$owner has no Clang size"
                },
            )
            val alignmentBytes = requireAlignment(
                owner,
                bitsToBytes(
                    metric = "alignment",
                    owner = owner,
                    bits = requireNotNull(ClangAlignOf.get(declaration)) {
                        "$owner has no Clang alignment"
                    },
                ),
            )
            val members = declaration.members()
                .filterIsInstance<Declaration.Variable>()
                .filterNot(Skip::isPresent)
                .map { field ->
                    createMemberLayout(declaration, field, sizeBytes)
                }

            validateMembers(declaration, sizeBytes, members)
            return KotlinJvmRecordLayout(
                declaration = declaration,
                sizeBytes = sizeBytes,
                alignmentBytes = alignmentBytes,
                members = members,
            )
        }

        private fun createMemberLayout(
            declaration: Declaration.Scoped,
            field: Declaration.Variable,
            recordSizeBytes: Long,
        ): KotlinJvmRecordMemberLayout {
            val owner = "${declaration.name()}.${field.name()}"
            val offsetBytes = bitsToBytes(
                metric = "offset",
                owner = owner,
                bits = requireNotNull(ClangOffsetOf.get(field)) {
                    "$owner has no Clang offset"
                },
            )
            val sizeBytes = bitsToBytes(
                metric = "size",
                owner = owner,
                bits = requireNotNull(ClangSizeOf.get(field)) {
                    "$owner has no Clang size"
                },
            )
            require(offsetBytes <= recordSizeBytes && sizeBytes <= recordSizeBytes - offsetBytes) {
                "$owner exceeds the record size"
            }
            val alignmentBytes = requireAlignment(
                owner,
                bitsToBytes(
                    metric = "alignment",
                    owner = owner,
                    bits = requireNotNull(ClangAlignOf.get(field)) {
                        "$owner has no Clang alignment"
                    },
                ),
            )
            return KotlinJvmRecordMemberLayout(
                field = field,
                cName = field.name(),
                offsetBytes = offsetBytes,
                sizeBytes = sizeBytes,
                alignmentBytes = alignmentBytes,
            )
        }

        private fun validateMembers(
            declaration: Declaration.Scoped,
            sizeBytes: Long,
            members: List<KotlinJvmRecordMemberLayout>,
        ) {
            when (declaration.kind()) {
                Declaration.Scoped.Kind.STRUCT -> {
                    var previousEnd = 0L
                    members.forEach { member ->
                        require(member.offsetBytes >= previousEnd) {
                            "${declaration.name()}.${member.cName} overlaps the preceding field"
                        }
                        require(member.offsetBytes + member.sizeBytes <= sizeBytes) {
                            "${declaration.name()}.${member.cName} exceeds the record size"
                        }
                        previousEnd = member.offsetBytes + member.sizeBytes
                    }
                }
                Declaration.Scoped.Kind.UNION -> members.forEach { member ->
                    require(member.offsetBytes == 0L) {
                        "${declaration.name()}.${member.cName} has non-zero union offset: ${member.offsetBytes}"
                    }
                }
                else -> error("Expected struct or union, found ${declaration.kind()}")
            }
        }

        private fun bitsToBytes(metric: String, owner: String, bits: Long): Long {
            require(bits >= 0L) { "$owner has negative $metric: $bits bits" }
            require(bits % 8L == 0L) { "$owner has non-byte-addressable $metric: $bits bits" }
            return bits / 8L
        }

        private fun requireAlignment(owner: String, bytes: Long): Long {
            require(bytes > 0L && bytes.countOneBits() == 1) {
                "$owner has invalid byte alignment: $bytes"
            }
            return bytes
        }
    }
}
