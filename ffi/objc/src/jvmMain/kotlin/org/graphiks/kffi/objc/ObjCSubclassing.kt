package org.graphiks.kffi.objc

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout

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
}