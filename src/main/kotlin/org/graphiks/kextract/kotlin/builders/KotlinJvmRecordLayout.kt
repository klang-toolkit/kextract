package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.DeclarationImpl.ClangUnnamedRecord

internal data class KotlinJvmRecordMemberLayout(
    val field: Declaration.Variable,
    val kotlinName: String,
    val cName: String,
    val offsetBytes: Long,
    val sizeBytes: Long,
    val alignmentBytes: Long,
    val layoutExpression: String,
)

internal data class KotlinJvmRecordLayout(
    val declaration: Declaration.Scoped,
    val sizeBytes: Long,
    val alignmentBytes: Long,
    val members: List<KotlinJvmRecordMemberLayout>,
) {
    fun field(cName: String): KotlinJvmRecordMemberLayout =
        requireNotNull(members.firstOrNull { it.cName == cName }) { "no field '$cName' in ${declaration.name()}" }

    fun render(builder: SourceBuilder) {
        val memoryLayout = "java.lang.foreign.MemoryLayout"
        val layoutElements = layoutElements(memoryLayout)

        builder.appendLine("val layout: java.lang.foreign.GroupLayout = $memoryLayout.${factory()}(")
        builder.indent()
        layoutElements.forEachIndexed { index, expression ->
            val comma = if (index < layoutElements.lastIndex) "," else ""
            builder.appendLine("$expression$comma")
        }
        builder.unindent()
        builder.appendLine(
            ").withByteAlignment($alignmentBytes)${recordNameSuffix()}",
        )
    }

    internal fun renderExpression(): String {
        val memoryLayout = "java.lang.foreign.MemoryLayout"
        return "$memoryLayout.${factory()}(${layoutElements(memoryLayout).joinToString(", ")})" +
            ".withByteAlignment($alignmentBytes)${recordNameSuffix()}"
    }

    private fun recordNameSuffix(): String =
        if (ClangUnnamedRecord.isPresent(declaration)) "" else ".withName(\"${declaration.name()}\")"

    private fun factory(): String = when (declaration.kind()) {
        Declaration.Scoped.Kind.STRUCT -> "structLayout"
        Declaration.Scoped.Kind.UNION -> "unionLayout"
        else -> error("Expected struct or union, found ${declaration.kind()}")
    }

    private fun layoutElements(memoryLayout: String): List<String> = when (declaration.kind()) {
        Declaration.Scoped.Kind.STRUCT -> structLayoutElements(memoryLayout)
        Declaration.Scoped.Kind.UNION -> unionLayoutElements(memoryLayout)
        else -> error("Expected struct or union, found ${declaration.kind()}")
    }

    private fun structLayoutElements(memoryLayout: String): List<String> = buildList {
        var previousEnd = 0L
        members.forEach { member ->
            val gap = member.offsetBytes - previousEnd
            if (gap > 0L) add("$memoryLayout.paddingLayout($gap)")
            add("${member.layoutExpression}.withName(\"${member.cName}\")")
            previousEnd = member.offsetBytes + member.sizeBytes
        }
        val finalPadding = sizeBytes - previousEnd
        if (finalPadding > 0L) add("$memoryLayout.paddingLayout($finalPadding)")
    }

    private fun unionLayoutElements(memoryLayout: String): List<String> = buildList {
        members.forEach { member ->
            add("${member.layoutExpression}.withName(\"${member.cName}\")")
        }
        val largestMemberSize = members.maxOfOrNull(KotlinJvmRecordMemberLayout::sizeBytes) ?: 0L
        if (largestMemberSize < sizeBytes) add("$memoryLayout.paddingLayout($sizeBytes)")
    }
}
