// src/main/kotlin/org/openjdk/kextract/kotlin/builders/KotlinToplevelBuilder.kt
package org.openjdk.kextract.kotlin.builders

import org.openjdk.kextract.Declaration
import org.openjdk.kextract.kotlin.models.KotlinSourceFile
import org.openjdk.kextract.kotlin.utils.KotlinNameMangler

/**
 * Top-level builder for Kotlin files.
 * Coordinates generation of all declarations (structs, functions, ObjC classes, etc.).
 */
class KotlinToplevelBuilder(
    private val targetPackage: String,
    className: String,
    private val headerName: String
) : Declaration.Visitor<Unit> {
    val className: String = className
    private val builder = SourceBuilder()
    private val files = mutableListOf<KotlinSourceFile>()
    private val headerBuilder = KotlinHeaderBuilder(builder, this)
    private val structBuilder = KotlinStructBuilder(builder, this)
    private val typedefBuilder = KotlinTypedefBuilder(builder, this)
    private val functionalBuilder = KotlinFunctionalBuilder(builder, this)
    private val objcClassBuilder = KotlinObjCClassBuilder(builder, this)
    private val objcProtocolBuilder = KotlinObjCProtocolBuilder(builder, this)
    private val objcCategoryBuilder = KotlinObjCCategoryBuilder(builder, this)

    /** True if any ObjC declaration was encountered — triggers ObjCRuntime.kt emission. */
    var needsObjCRuntime: Boolean = false
        private set

    init {
        // Package declaration
        if (targetPackage.isNotEmpty()) {
            builder.appendLine("package ${targetPackage}")
            builder.appendLine()
        }

        // Standard imports
        builder.appendLine("import java.lang.invoke.*")
        builder.appendLine("import java.lang.foreign.*")
        builder.appendLine("import java.lang.foreign.MemoryLayout.PathElement.*")
        builder.appendLine()

        // Helper constants for layouts
        builder.appendLine("private object kextract_runtime {")
        builder.indent()
        builder.appendLine("val C_BOOL: ValueLayout = ValueLayout.JAVA_BOOLEAN")
        builder.appendLine("val C_CHAR: ValueLayout = ValueLayout.JAVA_BYTE")
        builder.appendLine("val C_SHORT: ValueLayout = ValueLayout.JAVA_SHORT")
        builder.appendLine("val C_INT: ValueLayout = ValueLayout.JAVA_INT")
        builder.appendLine("val C_LONG: ValueLayout = ValueLayout.JAVA_LONG")
        builder.appendLine("val C_LONG_LONG: ValueLayout = ValueLayout.JAVA_LONG")
        builder.appendLine("val C_FLOAT: ValueLayout = ValueLayout.JAVA_FLOAT")
        builder.appendLine("val C_DOUBLE: ValueLayout = ValueLayout.JAVA_DOUBLE")
        builder.appendLine("val C_POINTER: ValueLayout = ValueLayout.ADDRESS")
        builder.unindent()
        builder.appendLine("}")
        builder.appendLine()
    }

    override fun visitScoped(decl: Declaration.Scoped): Unit {
        // Check if this is a struct or union by looking at kind
        when (decl.kind()) {
            Declaration.Scoped.Kind.STRUCT, Declaration.Scoped.Kind.UNION -> {
                // Delegate to structBuilder (which handles both structs and unions)
                if (decl.kind() == Declaration.Scoped.Kind.STRUCT) {
                    structBuilder.visitStruct(decl)
                } else {
                    structBuilder.visitUnion(decl)
                }
            }

            else -> {
                // For TOPLEVEL, process all members
                for (d in decl.members()) {
                    d.accept(this)
                }
            }
        }

        // Only add file for TOPLEVEL scoped (not for nested structs/unions)
        if (decl.kind() == Declaration.Scoped.Kind.TOPLEVEL) {
            files.add(
                KotlinSourceFile(
                    targetPackage,
                    className,
                    builder.toString()
                )
            )
        }
        return Unit
    }

    override fun visitFunction(decl: Declaration.Function): Unit {
        headerBuilder.visitFunction(decl)
        return Unit
    }

    override fun visitVariable(decl: Declaration.Variable): Unit {
        headerBuilder.visitVariable(decl)
        return Unit
    }

    override fun visitTypedef(decl: Declaration.Typedef): Unit {
        typedefBuilder.visitTypedef(decl)
        return Unit
    }

    override fun visitConstant(decl: Declaration.Constant): Unit {
        headerBuilder.visitConstant(decl)
        return Unit
    }

    override fun visitObjCClass(decl: Declaration.ObjCClass): Unit {
        needsObjCRuntime = true
        objcClassBuilder.visitClass(decl)
    }

    override fun visitObjCProtocol(decl: Declaration.ObjCProtocol): Unit {
        needsObjCRuntime = true
        objcProtocolBuilder.visitProtocol(decl)
    }

    override fun visitObjCCategory(decl: Declaration.ObjCCategory): Unit {
        needsObjCRuntime = true
        objcCategoryBuilder.visitCategory(decl)
    }

    fun getFiles(): List<KotlinSourceFile> = files

    fun javaName(name: String): String = KotlinNameMangler.mangle(name)

    fun lookupName(decl: Declaration): String = decl.name()
}
