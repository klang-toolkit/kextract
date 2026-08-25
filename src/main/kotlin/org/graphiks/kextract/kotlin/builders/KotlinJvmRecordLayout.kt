package org.graphiks.kextract.kotlin.builders

import org.graphiks.kextract.Declaration

internal data class KotlinJvmRecordMemberLayout(
    val field: Declaration.Variable,
    val cName: String,
    val offsetBytes: Long,
    val sizeBytes: Long,
    val alignmentBytes: Long,
)

internal data class KotlinJvmRecordLayout(
    val declaration: Declaration.Scoped,
    val sizeBytes: Long,
    val alignmentBytes: Long,
    val members: List<KotlinJvmRecordMemberLayout>,
) {
    fun field(cName: String): KotlinJvmRecordMemberLayout =
        requireNotNull(members.firstOrNull { it.cName == cName }) { "no field '$cName' in ${declaration.name()}" }
}
