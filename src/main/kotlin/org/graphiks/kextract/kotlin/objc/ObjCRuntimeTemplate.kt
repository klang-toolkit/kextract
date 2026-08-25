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
import java.lang.foreign.GroupLayout
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.SymbolLookup
import java.lang.foreign.UnionLayout
import java.lang.foreign.ValueLayout
import java.util.concurrent.ConcurrentHashMap

/**
 * Classifies C POD aggregate returns for the Objective-C entry-point split.
 *
 * Apple x86-64 follows the SysV aggregate rule: values larger than two
 * eightbytes, or layouts containing an unaligned member, use the memory-return
 * convention and therefore objc_msgSend_stret. Naturally aligned,
 * byte-addressable generated scalar, array, union, and nested-record layouts
 * are within this classifier's supported domain; unsafe packed surface records
 * are rejected while their layouts are planned.
 */
internal fun objcStructReturnUsesStret(returnLayout: GroupLayout, architecture: String): Boolean {
    if (architecture != "x86_64" && architecture != "amd64") return false
    return returnLayout.byteSize() > 16L || objcLayoutHasUnalignedMember(returnLayout)
}

private fun objcLayoutHasUnalignedMember(layout: GroupLayout): Boolean {
    if (layout is UnionLayout) {
        return layout.memberLayouts().any { member ->
            member is GroupLayout && objcLayoutHasUnalignedMember(member)
        }
    }
    var offset = 0L
    for (member in layout.memberLayouts()) {
        if (offset % member.byteAlignment() != 0L) return true
        if (member is GroupLayout && objcLayoutHasUnalignedMember(member)) return true
        offset += member.byteSize()
    }
    return false
}

/**
 * Low-level Objective-C runtime bridge via Panama FFI.
 *
 * Use [sel], [getClass] and [msgSend] to call Objective-C methods from Kotlin/JVM.
 * All ObjC object references are represented as [MemorySegment].
 *
 * JVM threads have no implicit autorelease pool. Wrap calls to factory methods
 * (those not starting with alloc/new/copy/mutableCopy) inside [autoreleasePool] to
 * ensure autoreleased objects are reclaimed correctly.
 */
object ObjCRuntime {

    private val arena: Arena = Arena.global()
    private val objcLib: SymbolLookup = run {
        // On macOS the JVM links libobjc, so try the process loader first.
        val loaderSymbol = SymbolLookup.loaderLookup().find("objc_msgSend")
        if (loaderSymbol.isPresent) {
            SymbolLookup.loaderLookup()
        } else {
            // Fallback: load by absolute path (macOS 12+)
            SymbolLookup.libraryLookup("/usr/lib/libobjc.dylib", arena)
        }
    }
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

    private val ARCH: String = System.getProperty("os.arch", "")

    /** Address of objc_msgSend_stret — only valid on x86-64; null on ARM64. */
    val objcMsgSendStretAddr: MemorySegment? = if (ARCH == "x86_64" || ARCH == "amd64")
        objcLib.find("objc_msgSend_stret").orElse(null)
    else null

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
     * Wraps a struct-by-value argument together with its [GroupLayout].
     *
     * When an ObjC method accepts a struct parameter by value (e.g. `NSRect`, `NSPoint`),
     * the argument must be passed as a raw [MemorySegment] containing the struct bytes, but
     * the [FunctionDescriptor] must use the matching [GroupLayout] (not [ValueLayout.ADDRESS]).
     * Wrap the segment in [ObjCStructArg] so [msgSend] can apply the correct layout.
     */
    internal data class ObjCStructArg(val segment: MemorySegment, val layout: GroupLayout)

    /**
     * Sends an ObjC message to [receiver] with selector [selector] and [args].
     *
     * - [returnLayout] = null for void-returning methods
     * - [returnLayout] = [ValueLayout.ADDRESS] for id/pointer-returning methods
     * - [returnLayout] = [ValueLayout.JAVA_LONG] / [ValueLayout.JAVA_DOUBLE] etc. for primitives
     *
     * Each arg must be: [MemorySegment], [Long], [Int], [Double], [Float], [Byte], [Short],
     * [Boolean], or [ObjCStructArg] for struct-by-value arguments.
     *
     * Layouts are computed from the *original* args (before unwrapping) so that [ObjCStructArg]
     * contributes its [GroupLayout] rather than [ValueLayout.ADDRESS] — this is the correct order
     * described in issue #22 Bug 5.
     */
    fun msgSend(returnLayout: MemoryLayout?, receiver: MemorySegment, selector: MemorySegment, vararg args: Any): Any? {
        val argLayouts = args.map { layoutFor(it) }.toTypedArray()   // (Bug 5) layouts before unwrap
        val baseLayouts = arrayOf(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
        val desc = if (returnLayout == null)
            FunctionDescriptor.ofVoid(*baseLayouts, *argLayouts)
        else
            FunctionDescriptor.of(returnLayout, *baseLayouts, *argLayouts)
        val handle = linker.downcallHandle(objcMsgSendAddr, desc)
        val unwrapped = args.map { unwrap(it) }.toTypedArray()
        return handle.invokeWithArguments(receiver, selector, *unwrapped)
    }

    /**
     * Sends a method returning a struct by value through the ABI-selected entry point.
     *
     * Panama requires a [GroupLayout] in the [FunctionDescriptor] for struct-returning calls and
     * inserts a [SegmentAllocator] as the first argument to the downcall handle.  The struct bytes
     * are written into heap-backed storage allocated here and returned as a [MemorySegment].
     *
     * On x86-64 [objcStructReturnUsesStret] selects [objc_msgSend_stret] only for memory-class
     * aggregates. Register-class structs and all ARM64 structs use regular [objc_msgSend].
     *
     * Important: the allocator must be backed by a [DoubleArray] (8-byte aligned) rather than a
     * [ByteArray] to satisfy Panama's alignment constraints for `double`-containing structs such
     * as [NSRect].
     */
    fun msgSendStruct(returnLayout: GroupLayout, receiver: MemorySegment, selector: MemorySegment, vararg args: Any): MemorySegment {
        val addr = if (objcStructReturnUsesStret(returnLayout, ARCH)) {
            requireNotNull(objcMsgSendStretAddr) { "objc_msgSend_stret is unavailable on ${'$'}ARCH" }
        } else {
            objcMsgSendAddr
        }
        val argLayouts = args.map { layoutFor(it) }.toTypedArray()
        val baseLayouts = arrayOf<MemoryLayout>(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
        val desc = FunctionDescriptor.of(returnLayout, *baseLayouts, *argLayouts)
        val handle = linker.downcallHandle(addr, desc)
        // Allocate struct-return storage. DoubleArray guarantees 8-byte alignment so that
        // structs containing `double` fields (NSRect, NSPoint, …) pass Panama's alignment check.
        val byteSize = returnLayout.byteSize().toInt()
        val heapDoubles = DoubleArray((byteSize + 7) / Double.SIZE_BYTES)
        val heapSeg = MemorySegment.ofArray(heapDoubles).asSlice(0, byteSize.toLong())
        val allocator = SegmentAllocator.prefixAllocator(heapSeg)
        val unwrapped = args.map { unwrap(it) }.toTypedArray()
        // Panama inserts the allocator as the implicit first argument for GroupLayout returns.
        return handle.invokeWithArguments(allocator, receiver, selector, *unwrapped) as MemorySegment
    }

    // ── Autorelease pool ──────────────────────────────────────────────────────

    private val autoreleasePoolPushAddr: MemorySegment =
        objcLib.find("objc_autoreleasePoolPush").orElseThrow { UnsatisfiedLinkError("objc_autoreleasePoolPush not found") }

    private val autoreleasePoolPopAddr: MemorySegment =
        objcLib.find("objc_autoreleasePoolPop").orElseThrow { UnsatisfiedLinkError("objc_autoreleasePoolPop not found") }

    @PublishedApi internal val autoreleasePoolPushHandle = linker.downcallHandle(
        autoreleasePoolPushAddr,
        FunctionDescriptor.of(ValueLayout.ADDRESS)
    )

    @PublishedApi internal val autoreleasePoolPopHandle = linker.downcallHandle(
        autoreleasePoolPopAddr,
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS)
    )

    /**
     * Runs [block] within an ObjC autorelease pool.
     * Factory methods that return autoreleased objects should be called inside this scope.
     *
     * Example:
     * ```kotlin
     * val str = ObjCRuntime.autoreleasePool {
     *     NSString.stringWithUTF8String(cStr)
     * }
     * ```
     */
    inline fun <T> autoreleasePool(block: () -> T): T {
        val token = autoreleasePoolPushHandle.invokeExact() as MemorySegment
        return try {
            block()
        } finally {
            autoreleasePoolPopHandle.invokeExact(token)
        }
    }

    // ── Convenience helpers ───────────────────────────────────────────────────

    /**
     * Creates an NSString from a Kotlin [String] using [NSString stringWithUTF8String:].
     * The returned MemorySegment is an autoreleased NSString object.
     */
    fun newNSString(alloc: Arena, value: String): MemorySegment {
        val cls = getClass("NSString")
        val cStr = alloc.allocateFrom(value)
        return msgSend(ValueLayout.ADDRESS, cls, sel("stringWithUTF8String:"), cStr) as MemorySegment
    }

    /**
     * Converts an NSString [MemorySegment] to a Kotlin [String]
     * by sending the [UTF8String] message and reading the C string.
     *
     * The raw pointer returned from native code has byteSize=0 in Panama's
     * safety model. We reinterpret it with Long.MAX_VALUE so [getString] can
     * scan for the null terminator.
     *
     * Returns an empty string if [nsString] is NULL or if [UTF8String] returns NULL
     * (e.g. deallocated object, encoding error).
     */
    fun toJavaString(nsString: MemorySegment): String {
        if (nsString == MemorySegment.NULL) return ""
        val utf8Ptr = (msgSend(ValueLayout.ADDRESS, nsString, sel("UTF8String")) as MemorySegment)
            .reinterpret(Long.MAX_VALUE)
        if (utf8Ptr == MemorySegment.NULL) return ""
        return utf8Ptr.getString(0)
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    private fun layoutFor(arg: Any): MemoryLayout = when (arg) {
        is ObjCStructArg -> arg.layout              // struct-by-value: use the GroupLayout
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

    /** Extracts the raw Panama-compatible value from [arg], unwrapping [ObjCStructArg]. */
    private fun unwrap(arg: Any): Any = when (arg) {
        is ObjCStructArg -> arg.segment
        else             -> arg
    }
}
""".trimIndent()
        return KotlinSourceFile(packageName, "ObjCRuntime", contents)
    }
}
