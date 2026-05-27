package org.graphiks.kextract.kotlin.objc

import org.graphiks.kextract.kotlin.models.KotlinSourceFile

/**
 * Generates the ObjCRuntime.kt helper file that wraps the Objective-C runtime
 * C API (objc_msgSend, sel_registerName, objc_getClass) via Panama FFI.
 *
 * This file is emitted at most once per kextract invocation, into the same
 * package as the generated class bindings, whenever ObjC declarations are present.
 */
object ObjCRuntimeTemplate {

    fun generate(packageName: String): KotlinSourceFile {
        val pkg = if (packageName.isNotEmpty()) "package $packageName\n\n" else ""
        val contents = """
${pkg}import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.util.concurrent.ConcurrentHashMap

/**
 * Low-level Objective-C runtime bridge via Panama FFI.
 *
 * Use [sel], [getClass] and [msgSend] to call Objective-C methods from Kotlin/JVM.
 * All ObjC object references are represented as [MemorySegment].
 */
object ObjCRuntime {

    private val arena: Arena = Arena.global()
    private val objcLib: SymbolLookup = SymbolLookup.libraryLookup("/usr/lib/libobjc.dylib", arena)
    private val linker: Linker = Linker.nativeLinker()

    // ── Caches ────────────────────────────────────────────────────────────────

    private val selectorCache = ConcurrentHashMap<String, MemorySegment>()
    private val classCache    = ConcurrentHashMap<String, MemorySegment>()

    // ── Bootstrapped handles ──────────────────────────────────────────────────

    private val selRegisterNameAddr: MemorySegment =
        objcLib.find("sel_registerName").orElseThrow { UnsatisfiedLinkError("sel_registerName not found in libobjc") }

    private val objcGetClassAddr: MemorySegment =
        objcLib.find("objc_getClass").orElseThrow { UnsatisfiedLinkError("objc_getClass not found in libobjc") }

    /** Address of objc_msgSend — exposed so generated code can build typed handles. */
    val objcMsgSendAddr: MemorySegment =
        objcLib.find("objc_msgSend").orElseThrow { UnsatisfiedLinkError("objc_msgSend not found in libobjc") }

    private val selRegisterNameHandle = linker.downcallHandle(
        selRegisterNameAddr,
        FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
    )

    private val objcGetClassHandle = linker.downcallHandle(
        objcGetClassAddr,
        FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
    )

    // ── Public API ────────────────────────────────────────────────────────────

    /**
     * Returns the selector for [name], cached after the first lookup.
     * Example: `ObjCRuntime.sel("stringWithUTF8String:")`
     */
    fun sel(name: String): MemorySegment = selectorCache.getOrPut(name) {
        val cStr = arena.allocateFrom(name)
        selRegisterNameHandle.invokeExact(cStr) as MemorySegment
    }

    /**
     * Returns the Class object for [name], cached after the first lookup.
     * Example: `ObjCRuntime.getClass("NSString")`
     */
    fun getClass(name: String): MemorySegment = classCache.getOrPut(name) {
        val cStr = arena.allocateFrom(name)
        objcGetClassHandle.invokeExact(cStr) as MemorySegment
    }

    /**
     * Sends an ObjC message to [receiver] with selector [selector] and [args].
     *
     * - [returnLayout] = null for void-returning methods
     * - [returnLayout] = ValueLayout.ADDRESS for id/pointer-returning methods
     * - [returnLayout] = ValueLayout.JAVA_LONG / JAVA_INT / JAVA_DOUBLE etc. for primitives
     *
     * Each arg must be a Panama-compatible type: MemorySegment, Long, Int, Double, Float, Byte, Short, Boolean.
     */
    fun msgSend(returnLayout: MemoryLayout?, receiver: MemorySegment, selector: MemorySegment, vararg args: Any): Any? {
        val argLayouts = args.map { layoutFor(it) }.toTypedArray()
        val baseLayouts = arrayOf(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
        val desc = if (returnLayout == null)
            FunctionDescriptor.ofVoid(*baseLayouts, *argLayouts)
        else
            FunctionDescriptor.of(returnLayout, *baseLayouts, *argLayouts)
        val handle = linker.downcallHandle(objcMsgSendAddr, desc)
        val allArgs: Array<Any> = arrayOf(receiver, selector, *args)
        return handle.invokeWithArguments(*allArgs)
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private fun layoutFor(arg: Any): MemoryLayout = when (arg) {
        is MemorySegment -> ValueLayout.ADDRESS
        is Long          -> ValueLayout.JAVA_LONG
        is Int           -> ValueLayout.JAVA_INT
        is Double        -> ValueLayout.JAVA_DOUBLE
        is Float         -> ValueLayout.JAVA_FLOAT
        is Byte          -> ValueLayout.JAVA_BYTE
        is Short         -> ValueLayout.JAVA_SHORT
        is Boolean       -> ValueLayout.JAVA_BOOLEAN
        else             -> throw IllegalArgumentException("Unsupported ObjC argument type: ${'$'}{arg::class.qualifiedName}")
    }
}
""".trimIndent()
        return KotlinSourceFile(packageName, "ObjCRuntime", contents)
    }
}
