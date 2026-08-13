package org.graphiks.kextract.kotlin.abi

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.ClangAlignOf
import org.graphiks.kextract.DeclarationImpl.ClangOffsetOf
import org.graphiks.kextract.DeclarationImpl.ClangSizeOf
import org.graphiks.kextract.DeclarationImpl.Skip
import java.util.IdentityHashMap

data class AndroidFieldLayout(
    val field: Declaration.Variable,
    val cName: String,
    val offsetBytes: Long,
    val sizeBytes: Long,
    val alignmentBytes: Long,
)

data class AndroidRecordLayout(
    val declaration: Declaration.Scoped,
    val sizeBytes: Long,
    val alignmentBytes: Long,
    val fields: List<AndroidFieldLayout>,
) {
    fun field(name: String): AndroidFieldLayout =
        requireNotNull(fields.firstOrNull { it.cName == name }) { "no field '$name' in ${declaration.name()}" }
}

class AndroidRecordLayoutPlan private constructor(
    private val layouts: IdentityHashMap<Declaration.Scoped, AndroidRecordLayout>,
) {
    operator fun get(declaration: Declaration.Scoped): AndroidRecordLayout =
        requireNotNull(layouts[declaration]) { "No Android record layout planned for ${declaration.name()}" }

    fun has(declaration: Declaration.Scoped): Boolean = layouts.containsKey(declaration)

    companion object {
        fun create(scoped: Declaration.Scoped): AndroidRecordLayoutPlan {
            val layouts = IdentityHashMap<Declaration.Scoped, AndroidRecordLayout>()
            fun collect(declaration: Declaration) {
                if (declaration !is Declaration.Scoped) return
                if (
                    !Skip.isPresent(declaration) &&
                    declaration.kind() in setOf(Declaration.Scoped.Kind.STRUCT, Declaration.Scoped.Kind.UNION)
                ) {
                    layouts[declaration] = createLayout(declaration, mutableListOf())
                }
                declaration.members().forEach(::collect)
            }
            collect(scoped)
            return AndroidRecordLayoutPlan(layouts)
        }

        private fun createLayout(
            declaration: Declaration.Scoped,
            recordStack: MutableList<Declaration.Scoped>,
        ): AndroidRecordLayout {
            val cycleStart = recordStack.indexOfFirst { it === declaration }
            require(cycleStart < 0) {
                val cycle = recordStack.subList(cycleStart, recordStack.size) + declaration
                "Android record layout has a by-value cycle: ${cycle.joinToString(" -> ") { it.name() }}"
            }
            recordStack.add(declaration)
            try {
                val owner = declaration.name()
                val sizeBytes = bitsToBytes("size", owner, clangSize(declaration))
                val alignmentBytes = bitsToBytes("alignment", owner, clangAlign(declaration))
                val fields = declaration.members()
                    .filterIsInstance<Declaration.Variable>()
                    .filterNot(Skip::isPresent)
                    .map { field ->
                        val fieldOwner = "$owner.${field.name()}"
                        AndroidFieldLayout(
                            field = field,
                            cName = field.name(),
                            offsetBytes = bitsToBytes("offset", fieldOwner, clangOffset(field)),
                            sizeBytes = bitsToBytes("size", fieldOwner, clangSize(field)),
                            alignmentBytes = bitsToBytes("alignment", fieldOwner, clangAlign(field)),
                        )
                    }
                validate(declaration, sizeBytes, fields)
                return AndroidRecordLayout(
                    declaration = declaration,
                    sizeBytes = sizeBytes,
                    alignmentBytes = alignmentBytes,
                    fields = fields,
                )
            } finally {
                recordStack.removeAt(recordStack.lastIndex)
            }
        }

        private fun validate(declaration: Declaration.Scoped, sizeBytes: Long, fields: List<AndroidFieldLayout>) {
            when (declaration.kind()) {
                Declaration.Scoped.Kind.STRUCT -> {
                    var previousEnd = 0L
                    fields.forEach { field ->
                        require(field.offsetBytes >= previousEnd) {
                            "${declaration.name()}.${field.cName} overlaps the preceding field"
                        }
                        require(field.offsetBytes + field.sizeBytes <= sizeBytes) {
                            "${declaration.name()}.${field.cName} exceeds the record size"
                        }
                        previousEnd = field.offsetBytes + field.sizeBytes
                    }
                }
                Declaration.Scoped.Kind.UNION -> fields.forEach { field ->
                    require(field.offsetBytes == 0L) {
                        "${declaration.name()}.${field.cName} has non-zero union offset"
                    }
                }
                else -> error("Expected struct or union, found ${declaration.kind()}")
            }
        }

        private fun bitsToBytes(metric: String, owner: String, bits: Long): Long {
            require(bits >= 0L && bits % 8L == 0L) { "$owner has non-byte $metric: $bits bits" }
            return bits / 8L
        }

        private fun clangSize(declaration: Declaration): Long =
            requireNotNull(ClangSizeOf.get(declaration)) { "${declaration.name()} has no Clang size" }

        private fun clangAlign(declaration: Declaration): Long =
            requireNotNull(ClangAlignOf.get(declaration)) { "${declaration.name()} has no Clang alignment" }

        private fun clangOffset(field: Declaration.Variable): Long =
            requireNotNull(ClangOffsetOf.get(field)) { "${field.name()} has no Clang offset" }
    }
}
