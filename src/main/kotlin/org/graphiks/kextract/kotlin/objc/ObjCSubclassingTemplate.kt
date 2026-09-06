package org.graphiks.kextract.kotlin.objc

import org.graphiks.kextract.kotlin.models.KotlinSourceFile

/**
 * Generates the ObjCSubclassing.kt helper file that wraps the ObjC runtime
 * functions for dynamically creating ObjC subclasses at runtime
 * (objc_allocateClassPair, class_addMethod, class_addProtocol, etc.).
 *
 * Emitted at most once per kextract invocation, alongside [ObjCRuntimeTemplate].
 */
object ObjCSubclassingTemplate {

    fun generate(packageName: String): KotlinSourceFile {
        val pkg = if (packageName.isNotEmpty()) "package $packageName\n\n" else ""
        val contents = """
${pkg}import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.util.concurrent.ConcurrentHashMap

/** A managed implementation of an Objective-C method returning `BOOL` with no explicit argument. */
fun interface ObjCBooleanNoArgumentHandler {
    fun invoke(receiver: MemorySegment, selector: MemorySegment): Boolean
}

/**
 * A generated `BOOL(id, SEL)` implementation pointer.
 *
 * The callback is retained by [ObjCSubclassing] after successful installation because Objective-C
 * classes may invoke their methods for the rest of the process lifetime.
 */
class ObjCBooleanNoArgumentCallback internal constructor(
    @Suppress("unused") private val handler: ObjCBooleanNoArgumentHandler,
    internal val imp: MemorySegment,
)

/** A managed implementation of an Objective-C `void(id, SEL, SEL)` method. */
fun interface ObjCVoidSelectorHandler {
    fun invoke(receiver: MemorySegment, selector: MemorySegment, argument: MemorySegment)
}

/** A generated `void(id, SEL, SEL)` implementation pointer. */
class ObjCVoidSelectorCallback internal constructor(
    @Suppress("unused") private val handler: ObjCVoidSelectorHandler,
    internal val imp: MemorySegment,
)

/** A managed implementation of an Objective-C method returning an object with no explicit argument. */
fun interface ObjCObjectNoArgumentHandler {
    fun invoke(receiver: MemorySegment, selector: MemorySegment): MemorySegment
}

/** A generated `id(id, SEL)` implementation pointer. */
class ObjCObjectNoArgumentCallback internal constructor(
    @Suppress("unused") private val handler: ObjCObjectNoArgumentHandler,
    internal val imp: MemorySegment,
)

/**
 * Primitives for dynamically creating Objective-C subclasses
 * from Kotlin/JVM via Panama FFM.
 *
 * Wraps the ObjC runtime functions required to register classes:
 * objc_allocateClassPair, class_addMethod, class_addProtocol,
 * objc_registerClassPair.
 */
object ObjCSubclassing {

    private val arena: Arena = Arena.global()
    private val objcLib: SymbolLookup = run {
        val loaderSymbol = SymbolLookup.loaderLookup().find("objc_allocateClassPair")
        if (loaderSymbol.isPresent) SymbolLookup.loaderLookup()
        else SymbolLookup.libraryLookup("/usr/lib/libobjc.dylib", arena)
    }
    private val linker: Linker = Linker.nativeLinker()
    private val booleanNoArgumentDescriptor = FunctionDescriptor.of(
        ValueLayout.JAVA_BOOLEAN,
        ValueLayout.ADDRESS,
        ValueLayout.ADDRESS,
    )
    private val voidSelectorDescriptor = FunctionDescriptor.ofVoid(
        ValueLayout.ADDRESS,
        ValueLayout.ADDRESS,
        ValueLayout.ADDRESS,
    )
    private val objectNoArgumentDescriptor = FunctionDescriptor.of(
        ValueLayout.ADDRESS,
        ValueLayout.ADDRESS,
        ValueLayout.ADDRESS,
    )
    private val booleanNoArgumentHandle: MethodHandle by lazy {
        MethodHandles.lookup().findVirtual(
            ObjCBooleanNoArgumentHandler::class.java,
            "invoke",
            booleanNoArgumentDescriptor.toMethodType(),
        )
    }
    private val voidSelectorHandle: MethodHandle by lazy {
        MethodHandles.lookup().findVirtual(
            ObjCVoidSelectorHandler::class.java,
            "invoke",
            voidSelectorDescriptor.toMethodType(),
        )
    }
    private val objectNoArgumentHandle: MethodHandle by lazy {
        MethodHandles.lookup().findVirtual(
            ObjCObjectNoArgumentHandler::class.java,
            "invoke",
            objectNoArgumentDescriptor.toMethodType(),
        )
    }
    private val retainedBooleanNoArgumentCallbacks = ConcurrentHashMap<MethodKey, ObjCBooleanNoArgumentCallback>()
    private val retainedVoidSelectorCallbacks = ConcurrentHashMap<MethodKey, ObjCVoidSelectorCallback>()
    private val retainedObjectNoArgumentCallbacks = ConcurrentHashMap<MethodKey, ObjCObjectNoArgumentCallback>()

    // Class objc_allocateClassPair(Class superclass, const char *name, size_t extraBytes)
    private val allocateClassPair = linker.downcallHandle(
        objcLib.find("objc_allocateClassPair").orElseThrow {
            UnsatisfiedLinkError("objc_allocateClassPair not found")
        },
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_LONG,
        ),
    )

    // BOOL class_addMethod(Class cls, SEL name, IMP imp, const char *types)
    private val classAddMethod = linker.downcallHandle(
        objcLib.find("class_addMethod").orElseThrow {
            UnsatisfiedLinkError("class_addMethod not found")
        },
        FunctionDescriptor.of(
            ValueLayout.JAVA_BOOLEAN,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
        ),
    )

    // void objc_registerClassPair(Class cls)
    private val registerClassPair = linker.downcallHandle(
        objcLib.find("objc_registerClassPair").orElseThrow {
            UnsatisfiedLinkError("objc_registerClassPair not found")
        },
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS),
    )

    // BOOL class_addProtocol(Class cls, Protocol *proto)
    private val classAddProtocol = linker.downcallHandle(
        objcLib.find("class_addProtocol").orElseThrow {
            UnsatisfiedLinkError("class_addProtocol not found")
        },
        FunctionDescriptor.of(
            ValueLayout.JAVA_BOOLEAN,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
        ),
    )

    // Protocol *objc_getProtocol(const char *name)
    private val objcGetProtocol = linker.downcallHandle(
        objcLib.find("objc_getProtocol").orElseThrow {
            UnsatisfiedLinkError("objc_getProtocol not found")
        },
        FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
    )

    /**
     * Allocates an ObjC class pair (class + metaclass) derived
     * from [superclassName]. The class must then be registered
     * via [registerClass] after adding methods/protocols.
     */
    fun allocateClass(superclassName: String, subclassName: String): MemorySegment {
        val superclass = ObjCRuntime.getClass(superclassName)
        val nameCStr = arena.allocateFrom(subclassName)
        return allocateClassPair.invokeExact(superclass, nameCStr, 0L) as MemorySegment
    }

    /**
     * Adds a method to a not-yet-registered class.
     * [typeEncoding] follows the ObjC grammar: e.g. `"v@:@"` for
     * `void (id, SEL, id)`.
     */
    fun addMethod(
        cls: MemorySegment,
        selName: String,
        imp: MemorySegment,
        typeEncoding: String,
    ): Boolean {
        val sel = ObjCRuntime.sel(selName)
        val typesCStr = arena.allocateFrom(typeEncoding)
        return classAddMethod.invokeExact(cls, sel, imp, typesCStr) as Boolean
    }

    /**
     * Creates a generated `BOOL(id, SEL)` callback implementation.
     *
     * The returned callback is retained automatically by [addBooleanNoArgumentMethod] after the
     * Objective-C runtime accepts the method installation.
     */
    fun booleanNoArgumentCallback(handler: ObjCBooleanNoArgumentHandler): ObjCBooleanNoArgumentCallback =
        ObjCBooleanNoArgumentCallback(
            handler = handler,
            imp = linker.upcallStub(
                booleanNoArgumentHandle.bindTo(handler),
                booleanNoArgumentDescriptor,
                arena,
            ),
        )

    /** Installs a generated `BOOL(id, SEL)` [callback] on an unregistered Objective-C class. */
    fun addBooleanNoArgumentMethod(
        cls: MemorySegment,
        selName: String,
        callback: ObjCBooleanNoArgumentCallback,
    ): Boolean {
        val sel = ObjCRuntime.sel(selName)
        val installed = addMethod(cls, selName, callback.imp, booleanNoArgumentEncoding())
        if (installed) {
            retainedBooleanNoArgumentCallbacks.putIfAbsent(MethodKey(cls.address(), sel.address()), callback)
        }
        return installed
    }

    /** Creates a generated `void(id, SEL, SEL)` callback implementation. */
    fun voidSelectorCallback(handler: ObjCVoidSelectorHandler): ObjCVoidSelectorCallback =
        ObjCVoidSelectorCallback(
            handler = handler,
            imp = linker.upcallStub(
                voidSelectorHandle.bindTo(handler),
                voidSelectorDescriptor,
                arena,
            ),
        )

    /** Installs a generated `void(id, SEL, SEL)` [callback] on an unregistered Objective-C class. */
    fun addVoidSelectorMethod(
        cls: MemorySegment,
        selName: String,
        callback: ObjCVoidSelectorCallback,
    ): Boolean {
        val sel = ObjCRuntime.sel(selName)
        val installed = addMethod(cls, selName, callback.imp, "v@::")
        if (installed) {
            retainedVoidSelectorCallbacks.putIfAbsent(MethodKey(cls.address(), sel.address()), callback)
        }
        return installed
    }

    /** Creates a generated `id(id, SEL)` callback implementation. */
    fun objectNoArgumentCallback(handler: ObjCObjectNoArgumentHandler): ObjCObjectNoArgumentCallback =
        ObjCObjectNoArgumentCallback(
            handler = handler,
            imp = linker.upcallStub(
                objectNoArgumentHandle.bindTo(handler),
                objectNoArgumentDescriptor,
                arena,
            ),
        )

    /** Installs a generated `id(id, SEL)` [callback] on an unregistered Objective-C class. */
    fun addObjectNoArgumentMethod(
        cls: MemorySegment,
        selName: String,
        callback: ObjCObjectNoArgumentCallback,
    ): Boolean {
        val sel = ObjCRuntime.sel(selName)
        val installed = addMethod(cls, selName, callback.imp, "@@:")
        if (installed) {
            retainedObjectNoArgumentCallbacks.putIfAbsent(MethodKey(cls.address(), sel.address()), callback)
        }
        return installed
    }

    /**
     * Registers the class pair with the ObjC runtime.
     * No further calls to [addMethod]/[addProtocol] are valid after this.
     */
    fun registerClass(cls: MemorySegment) {
        registerClassPair.invokeExact(cls)
    }

    /**
     * Declares a class's conformance to an ObjC protocol.
     * Silently ignored if the protocol is not found.
     */
    fun addProtocol(cls: MemorySegment, protocolName: String): Boolean {
        val nameCStr = arena.allocateFrom(protocolName)
        val proto = objcGetProtocol.invokeExact(nameCStr) as MemorySegment
        if (proto == MemorySegment.NULL) return false as Boolean
        return classAddProtocol.invokeExact(cls, proto) as Boolean
    }

    private fun booleanNoArgumentEncoding(): String = when (System.getProperty("os.arch")) {
        "aarch64", "arm64" -> "B@:"
        "amd64", "x86_64" -> "c@:"
        else -> error("Unsupported Objective-C host architecture: ${'$'}{System.getProperty("os.arch")}")
    }

    private data class MethodKey(val cls: Long, val selector: Long)
}
""".trimIndent()
        return KotlinSourceFile(packageName, "ObjCSubclassing", contents)
    }
}
