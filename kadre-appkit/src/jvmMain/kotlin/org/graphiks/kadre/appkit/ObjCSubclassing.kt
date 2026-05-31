/**
 * Primitives pour créer dynamiquement des sous-classes Objective-C
 * depuis Kotlin/JVM via Panama FFM.
 *
 * Wrappe les fonctions du runtime ObjC nécessaires à l'enregistrement
 * de classes : objc_allocateClassPair, class_addMethod, class_addProtocol,
 * objc_registerClassPair.
 *
 * Réservé à un usage interne au module `kadre-appkit`.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout

internal object ObjCSubclassing {

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
     * Alloue une paire de classes ObjC (classe + métaclasse) dérivée
     * de [superclassName]. La classe doit ensuite être enregistrée
     * via [registerClass] après ajout des méthodes/protocoles.
     */
    fun allocateClass(superclassName: String, subclassName: String): MemorySegment {
        val superclass = ObjCRuntime.getClass(superclassName)
        val nameCStr = arena.allocateFrom(subclassName)
        return allocateClassPair.invokeExact(superclass, nameCStr, 0L) as MemorySegment
    }

    /**
     * Ajoute une méthode à une classe non encore enregistrée.
     * [typeEncoding] suit la grammaire ObjC : ex. `"v@:@"` pour
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
     * Enregistre la paire de classes auprès du runtime ObjC.
     * Aucun appel à [addMethod]/[addProtocol] n'est possible après cet appel.
     */
    fun registerClass(cls: MemorySegment) {
        registerClassPair.invokeExact(cls)
    }

    /**
     * Déclare la conformance d'une classe à un protocole ObjC.
     * Ignoré silencieusement si le protocole n'est pas trouvé.
     */
    fun addProtocol(cls: MemorySegment, protocolName: String): Boolean {
        val nameCStr = arena.allocateFrom(protocolName)
        val proto = objcGetProtocol.invokeExact(nameCStr) as MemorySegment
        if (proto == MemorySegment.NULL) return false
        return classAddProtocol.invokeExact(cls, proto) as Boolean
    }
}
