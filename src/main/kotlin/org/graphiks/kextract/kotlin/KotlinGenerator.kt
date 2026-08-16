// src/main/kotlin/org/openjdk/kextract/kotlin/KotlinGenerator.kt
package org.graphiks.kextract.kotlin

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.callbacks.ValidatedCallbackBindings
import org.graphiks.kextract.cli.DllMap
import org.graphiks.kextract.kotlin.builders.KotlinKmpAndroidBuilder
import org.graphiks.kextract.kotlin.builders.KotlinKmpCommonBuilder
import org.graphiks.kextract.kotlin.builders.KotlinKmpJvmBuilder
import org.graphiks.kextract.kotlin.builders.KotlinKmpNativeBuilder
import org.graphiks.kextract.kotlin.builders.KotlinJvmRecordLayoutPlan
import org.graphiks.kextract.kotlin.builders.KotlinToplevelBuilder
import org.graphiks.kextract.kotlin.abi.AndroidRecordLayoutPlan
import org.graphiks.kextract.kotlin.abi.KotlinKmpAbiIndex
import org.graphiks.kextract.kotlin.callbacks.KotlinCallbackModel
import org.graphiks.kextract.kotlin.callbacks.KotlinDirectFunctionBindingModel
import org.graphiks.kextract.kotlin.models.KotlinSourceFile
import org.graphiks.kextract.kotlin.objc.ObjCRuntimeTemplate
import org.graphiks.kextract.kotlin.objc.ObjCSubclassingTemplate
import org.graphiks.kextract.kotlin.utils.KotlinIdentifierAllocator
import org.graphiks.kextract.pipeline.Options

internal val GENERATED_CALLBACK_RESERVED_IDENTIFIERS = setOf(
    "Callback",
    "CallbackType",
    "CallbackPolicy",
    "CallbackRegistration",
    "PreparedCallbackRegistration",
    "CallbackExceptionHandler",
    "CallbackRuntime",
    "CallbackRuntimeApi",
    "CallbackReference",
    "UnsafeCallbackRearmApi",
    "NativeAddress",
    "MemoryAllocator",
    "CString",
    "ArrayHolder",
    "FunctionDescriptor",
    "MethodHandle",
    "MethodHandles",
    "Linker",
    "Arena",
    "MemorySegment",
    "ValueLayout",
    "JvmStatic",
    "CValue",
    "COpaquePointer",
    "COpaquePointerVar",
    "staticCFunction",
    "OptIn",
    "Suppress",
    "UnsupportedOperationException",
)

/**
 * Main entry point for Kotlin code generation.
 * This class is callable from Java (via KextractTool).
 */
class KotlinGenerator {
    /**
     * Generates Kotlin source files from a C/ObjC AST.
     *
     * When Objective-C declarations are present an additional `ObjCRuntime.kt`
     * helper file is automatically included in the result so callers can use
     * `ObjCRuntime.msgSend` / `ObjCRuntime.sel` / `ObjCRuntime.getClass`.
     *
     * @param scoped The root declaration (parsed from C/ObjC headers).
     * @param headerName The name of the header file (e.g., "mylib.h").
     * @param targetPackage The target package (e.g., "org.mylib").
     * @param libraries Libraries to load at runtime (used to generate lookup code).
     * @param useSystemLoadLibrary When true use System.loadLibrary instead of libraryLookup.
     * @return List of generated Kotlin source files.
     */
    fun generate(
        scoped: Declaration.Scoped,
        headerName: String,
        targetPackage: String,
        libraries: List<Options.Library> = emptyList(),
        useSystemLoadLibrary: Boolean = false,
        splitOutput: Boolean = false,
        variadicArgs: Map<String, Int> = emptyMap(),
        win32Mode: Boolean = false,
        dllMap: DllMap? = null,
        useInitMethod: Boolean = false,
        multiplatform: Boolean = false,
        callbackBindings: ValidatedCallbackBindings = ValidatedCallbackBindings.EMPTY,
    ): List<KotlinSourceFile> = generateWithJvmNativeBundle(
        scoped,
        headerName,
        targetPackage,
        libraries,
        useSystemLoadLibrary,
        splitOutput,
        variadicArgs,
        win32Mode,
        dllMap,
        useInitMethod,
        multiplatform,
        callbackBindings,
        libraries,
        KotlinJvmNativeBundleIndex(emptyList()),
    )

    internal fun generateWithJvmNativeBundle(
        scoped: Declaration.Scoped,
        headerName: String,
        targetPackage: String,
        libraries: List<Options.Library> = emptyList(),
        useSystemLoadLibrary: Boolean = false,
        splitOutput: Boolean = false,
        variadicArgs: Map<String, Int> = emptyMap(),
        win32Mode: Boolean = false,
        dllMap: DllMap? = null,
        useInitMethod: Boolean = false,
        multiplatform: Boolean = false,
        callbackBindings: ValidatedCallbackBindings = ValidatedCallbackBindings.EMPTY,
        jvmNativeLibraries: List<Options.Library>,
        jvmNativeBundleIndex: KotlinJvmNativeBundleIndex,
    ): List<KotlinSourceFile> {
        val className = sanitizeClassName(headerName)
        if (multiplatform) {
            val namePlan = KotlinKmpNamePlan.create(scoped, callbackBindings)
            val abiIndex = KotlinKmpAbiIndex.create(scoped)
            val jvmRecordLayouts = KotlinJvmRecordLayoutPlan.create(scoped, namePlan, abiIndex)
            val androidRecordLayouts = AndroidRecordLayoutPlan.create(scoped)
            val callbackNames = KotlinIdentifierAllocator(namePlan.topLevelNames + namePlan.renderedRuntimeNames)
            val callbackModels = callbackBindings.callbacks.map { callback ->
                KotlinCallbackModel.from(callback, callbackNames)
            }
            val callbackModelsByCanonicalId = callbackModels.associateBy(KotlinCallbackModel::canonicalId)
            val directBindingModels = callbackBindings.directFunctionBindings.map { binding ->
                KotlinDirectFunctionBindingModel(
                    binding = binding,
                    preflightName = callbackNames.allocate(
                        "${binding.function.name()}CallbackBindingPreflight",
                        "callbackBindingPreflight",
                    ),
                )
            }
            return generateKmp(
                scoped,
                targetPackage,
                className,
                androidLibraryName(libraries, className),
                callbackModels,
                callbackModelsByCanonicalId,
                directBindingModels,
                callbackBindings,
                namePlan,
                jvmRecordLayouts,
                androidRecordLayouts,
                abiIndex,
                jvmNativeLibraries,
                jvmNativeBundleIndex,
                callbackNames,
            )
        }

        val toplevel = KotlinToplevelBuilder(
            targetPackage, className, headerName, libraries, useSystemLoadLibrary, splitOutput, variadicArgs,
            win32Mode, dllMap, useInitMethod,
        )
        scoped.accept(toplevel)
        return toplevel.getFiles().toMutableList().apply {
            if (toplevel.needsObjCRuntime) {
                add(ObjCRuntimeTemplate.generate(targetPackage))
                add(ObjCSubclassingTemplate.generate(targetPackage))
            }
        }
    }

    private fun generateKmp(
        scoped: Declaration.Scoped,
        targetPackage: String,
        className: String,
        androidLibraryName: String,
        callbackModels: List<KotlinCallbackModel>,
        callbackModelsByCanonicalId: Map<String, KotlinCallbackModel>,
        directBindingModels: List<KotlinDirectFunctionBindingModel>,
        callbackBindings: ValidatedCallbackBindings,
        namePlan: KotlinKmpNamePlan,
        jvmRecordLayouts: KotlinJvmRecordLayoutPlan,
        androidRecordLayouts: AndroidRecordLayoutPlan,
        abiIndex: KotlinKmpAbiIndex,
        jvmNativeLibraries: List<Options.Library>,
        jvmNativeBundleIndex: KotlinJvmNativeBundleIndex,
        privateNames: KotlinIdentifierAllocator,
    ): List<KotlinSourceFile> = buildList {
        KotlinKmpCommonBuilder(
            targetPackage,
            className,
            callbackModels,
            callbackModelsByCanonicalId,
            directBindingModels,
            callbackBindings,
            namePlan,
            abiIndex,
        ).also { scoped.accept(it); addAll(it.getFiles()) }
        KotlinKmpJvmBuilder(
            targetPackage,
            className,
            callbackModels,
            directBindingModels,
            namePlan,
            jvmRecordLayouts,
            abiIndex,
            jvmNativeLibraries,
            jvmNativeBundleIndex,
            privateNames,
        ).also { scoped.accept(it); addAll(it.getFiles()) }
        KotlinKmpAndroidBuilder(
            targetPackage,
            className,
            androidLibraryName,
            callbackModels,
            directBindingModels,
            namePlan,
            androidRecordLayouts,
            abiIndex,
        ).also { scoped.accept(it); addAll(it.getFiles()) }
        KotlinKmpNativeBuilder(
            targetPackage,
            className,
            callbackModels,
            directBindingModels,
            namePlan,
            abiIndex,
        ).also { scoped.accept(it); addAll(it.getFiles()) }
    }

    private fun sanitizeClassName(name: String): String =
        name.substringAfterLast('/')
            .substringAfterLast('\\')
            .replace(Regex("[^a-zA-Z0-9_]"), "_")
            .replace(Regex("^\\d+"), "_")

    private fun androidLibraryName(libraries: List<Options.Library>, className: String): String =
        when (libraries.size) {
            0 -> className.removeSuffix("_h")
            1 -> libraries.single().libSpec
            else -> error(
                "Android/JNA KMP generation requires zero or one library; found ${libraries.size}",
            )
        }
}
