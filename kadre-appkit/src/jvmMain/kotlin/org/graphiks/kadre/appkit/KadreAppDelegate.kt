/**
 * Kotlin implementation of `NSApplicationDelegate` via ObjC runtime subclassing.
 *
 * Uses Panama FFM upcall stubs to expose Kotlin static functions
 * (`@JvmStatic`) as Objective-C method implementations.
 *
 * Dispatch strategy: the ObjC class `KadreAppDelegateNative` does not embed
 * a pointer to the Kotlin delegate. Instead, instances are indexed in a
 * global [java.util.concurrent.ConcurrentHashMap] keyed by the memory
 * address (`MemorySegment.address()`) of the ObjC `self`. Since the first
 * argument of any ObjC upcall is `self`, this is enough to retrieve the
 * target Kotlin instance during a native callback.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.ffi.objc.NSApplicationTerminateReply
import org.graphiks.kadre.ffi.objc.ObjCRuntime
import org.graphiks.kadre.ffi.objc.ObjCSubclassing
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.ConcurrentHashMap

class KadreAppDelegate(
    private val handler: ApplicationHandler,
    private val eventLoop: ActiveEventLoop,
) {
    /** Pointer to the Objective-C object wrapped by this delegate. */
    val ptr: MemorySegment

    init {
        ensureClassRegistered()

        val cls = ObjCRuntime.getClass("KadreAppDelegateNative")
        val allocated = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            cls,
            ObjCRuntime.sel("alloc"),
        ) as MemorySegment
        ptr = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            allocated,
            ObjCRuntime.sel("init"),
        ) as MemorySegment

        delegateTable[ptr.address()] = this
    }

    /** Kotlin callback for `applicationDidFinishLaunching:`. */
    fun onDidFinishLaunching() {
        handler.canCreateSurfaces(eventLoop)
    }

    /**
     * Kotlin callback for `applicationShouldTerminate:`.
     *
     * Returns `NSTerminateNow` when the loop is already shutting down,
     * `NSTerminateCancel` otherwise — shutdown is driven on the Kadre side via
     * [ActiveEventLoop.exit].
     */
    fun onShouldTerminate(): Long {
        return if (eventLoop.isExiting) {
            NSApplicationTerminateReply.NSTerminateNow.value
        } else {
            NSApplicationTerminateReply.NSTerminateCancel.value
        }
    }

    companion object {
        /** Global table: ObjC memory address → associated Kotlin delegate. */
        private val delegateTable = ConcurrentHashMap<Long, KadreAppDelegate>()

        @Volatile
        private var classRegistered: Boolean = false

        @Synchronized
        internal fun ensureClassRegistered() {
            if (classRegistered) return

            val arena = Arena.global()
            val linker: Linker = Linker.nativeLinker()
            val lookup = MethodHandles.lookup()

            val cls = ObjCSubclassing.allocateClass("NSObject", "KadreAppDelegateNative")
            ObjCSubclassing.addProtocol(cls, "NSApplicationDelegate")

            // void applicationDidFinishLaunching(id self, SEL _cmd, id notification)
            val didFinishLaunchingHandle = lookup.findStatic(
                Callbacks::class.java,
                "applicationDidFinishLaunching",
                MethodType.methodType(
                    Void.TYPE,
                    MemorySegment::class.java,
                    MemorySegment::class.java,
                    MemorySegment::class.java,
                ),
            )
            val didFinishLaunchingStub = linker.upcallStub(
                didFinishLaunchingHandle,
                FunctionDescriptor.ofVoid(
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                ),
                arena,
            )

            // NSUInteger applicationShouldTerminate(id self, SEL _cmd, id sender)
            val shouldTerminateHandle = lookup.findStatic(
                Callbacks::class.java,
                "applicationShouldTerminate",
                MethodType.methodType(
                    java.lang.Long.TYPE,
                    MemorySegment::class.java,
                    MemorySegment::class.java,
                    MemorySegment::class.java,
                ),
            )
            val shouldTerminateStub = linker.upcallStub(
                shouldTerminateHandle,
                FunctionDescriptor.of(
                    ValueLayout.JAVA_LONG,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                ),
                arena,
            )

            ObjCSubclassing.addMethod(
                cls,
                "applicationDidFinishLaunching:",
                didFinishLaunchingStub,
                "v@:@",
            )
            ObjCSubclassing.addMethod(
                cls,
                "applicationShouldTerminate:",
                shouldTerminateStub,
                "Q@:@",
            )

            ObjCSubclassing.registerClass(cls)
            classRegistered = true
        }
    }

    /**
     * `@JvmStatic` trampolines invoked by the Panama upcall stubs.
     *
     * The static methods are essential: `Linker.upcallStub` cannot bind
     * instance methods because the ObjC `self` is passed as the first
     * argument, not via the Java receiver.
     */
    object Callbacks {
        @JvmStatic
        fun applicationDidFinishLaunching(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            @Suppress("UNUSED_PARAMETER") notification: MemorySegment,
        ) {
            delegateTable[self.address()]?.onDidFinishLaunching()
        }

        @JvmStatic
        fun applicationShouldTerminate(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            @Suppress("UNUSED_PARAMETER") sender: MemorySegment,
        ): Long {
            return delegateTable[self.address()]?.onShouldTerminate()
                ?: NSApplicationTerminateReply.NSTerminateNow.value
        }
    }
}
