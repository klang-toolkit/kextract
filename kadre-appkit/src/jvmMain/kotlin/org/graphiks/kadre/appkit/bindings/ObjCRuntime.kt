package org.graphiks.kadre.appkit.bindings

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.GroupLayout
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.util.concurrent.ConcurrentHashMap

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
    val objcMsgSendStretAddr: MemorySegment? = if (ARCH == "x86_64")
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
    data class ObjCStructArg(val segment: MemorySegment, val layout: GroupLayout)

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
     * Like [msgSend] but for methods returning a struct by value.
     *
     * Panama requires a [GroupLayout] in the [FunctionDescriptor] for struct-returning calls and
     * inserts a [SegmentAllocator] as the first argument to the downcall handle.  The struct bytes
     * are written into heap-backed storage allocated here and returned as a [MemorySegment].
     *
     * On x86-64 [objc_msgSend_stret] is used; on ARM64 the regular [objc_msgSend] entry point
     * handles struct returns in registers (the stret variant does not exist there).
     *
     * Important: the allocator must be backed by a [DoubleArray] (8-byte aligned) rather than a
     * [ByteArray] to satisfy Panama's alignment constraints for `double`-containing structs such
     * as [NSRect].
     */
    fun msgSendStret(returnLayout: GroupLayout, receiver: MemorySegment, selector: MemorySegment, vararg args: Any): MemorySegment {
        val addr = objcMsgSendStretAddr ?: objcMsgSendAddr
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
        // ObjC BOOL = signed char (1 byte) on Apple platforms. Panama's JAVA_BOOLEAN
        // doesn't match the variadic-arg ABI used by objc_msgSend on macOS — passing
        // a Boolean carrier to objc_msgSend raises `Cannot cast Boolean to Byte` in the
        // method-handle adapter. Encode Boolean as JAVA_BYTE and convert in [unwrap].
        // Upstream tracking: https://github.com/klang-toolkit/kextract/issues/30
        is Boolean       -> ValueLayout.JAVA_BYTE
        // @JvmInline value classes (rawValue) and NS_ENUM classes (value) — Bug 4 from
        // klang-toolkit/kextract#22 isn't fully fixed in v0.0.2: the generator only
        // unboxes when the underlying C declaration is ENUM, but NS_OPTIONS that boil
        // down to `typedef NSUInteger Foo` reach us as boxed wrappers. Reflection
        // covers the gap until klang-toolkit/kextract#25 lands.
        else             -> layoutForWrapped(arg)
    }

    private fun boolToByte(b: Boolean): Byte {
        return if (b) 1.toByte() else 0.toByte()
    }

    private fun layoutForWrapped(arg: Any): MemoryLayout {
        val cls = arg::class.java
        val field = runCatching { cls.getDeclaredField("rawValue") }.getOrNull()
            ?: runCatching { cls.getDeclaredField("value") }.getOrNull()
            ?: throw IllegalArgumentException("Unsupported ObjC argument type: ${arg::class.qualifiedName}")
        field.isAccessible = true
        return when (field.type) {
            java.lang.Long.TYPE    -> ValueLayout.JAVA_LONG
            java.lang.Integer.TYPE -> ValueLayout.JAVA_INT
            java.lang.Double.TYPE  -> ValueLayout.JAVA_DOUBLE
            java.lang.Float.TYPE   -> ValueLayout.JAVA_FLOAT
            java.lang.Byte.TYPE    -> ValueLayout.JAVA_BYTE
            java.lang.Short.TYPE   -> ValueLayout.JAVA_SHORT
            else -> throw IllegalArgumentException(
                "Unsupported rawValue/value type (${field.type}) in: ${arg::class.qualifiedName}")
        }
    }

    /**
     * Extracts the raw Panama-compatible value from [arg].
     *
     * - [ObjCStructArg] → its [MemorySegment]
     * - Primitives / [MemorySegment] → returned as-is
     * - `@JvmInline value class` / NS_ENUM wrapper → unboxed via reflection on
     *   `rawValue` (NS_OPTIONS) or `value` (NS_ENUM). Same rationale as
     *   [layoutForWrapped] — bridges the gap left by klang-toolkit/kextract#25.
     */
    private fun unwrap(arg: Any): Any = when (arg) {
        is ObjCStructArg -> arg.segment
        // BOOL must be encoded as Byte (see comment in [layoutFor]). Convert here.
        is Boolean -> boolToByte(arg as Boolean)
        is MemorySegment, is Long, is Int, is Double,
        is Float, is Byte, is Short -> arg
        else -> {
            val cls = arg::class.java
            val field = runCatching { cls.getDeclaredField("rawValue") }.getOrNull()
                ?: runCatching { cls.getDeclaredField("value") }.getOrNull()
                ?: throw IllegalArgumentException("Cannot unwrap ObjC argument type: ${arg::class.qualifiedName}")
            field.isAccessible = true
            field.get(arg) ?: throw IllegalArgumentException("Field is null for: ${arg::class.qualifiedName}")
        }
    }
}