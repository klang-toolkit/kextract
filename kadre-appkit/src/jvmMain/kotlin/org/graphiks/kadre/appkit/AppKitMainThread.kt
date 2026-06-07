package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference

/**
 * Synchronous main-queue executor for AppKit requests.
 *
 * Mirrors winit's `maybe_wait_on_main` shape: calls already on the AppKit main
 * thread run immediately; calls from other JVM threads are executed on the
 * dispatch main queue and the caller waits for the result.
 */
internal object AppKitMainThread {
    private val nextTaskId = AtomicLong(1L)
    private val tasks = ConcurrentHashMap<Long, Task<*>>()

    /**
     * On macOS 26+ the dyld shared cache removes individual dylib files, so
     * [SymbolLookup.libraryLookup] with paths like `/usr/lib/libSystem.B.dylib`
     * fails.  We resolve all dispatch symbols via dlsym with `RTLD_DEFAULT` (-2),
     * which searches every loaded image and the shared cache.
     */
    private val linker = Linker.nativeLinker()

    /** RTLD_DEFAULT pseudo-handle – searches all loaded images + dyld cache. */
    private val rtldDefault = MemorySegment.ofAddress(-2L)

    private fun findDispatchSymbol(name: String): MemorySegment {
        val dlsym = linker.downcallHandle(
            linker.defaultLookup().find("dlsym").orElseThrow {
                UnsatisfiedLinkError("dlsym not found in linker default lookup")
            },
            FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS)
        )
        val cStr = Arena.global().allocateFrom(name)
        val sym = dlsym.invokeExact(rtldDefault, cStr) as MemorySegment
        if (sym.address() == 0L) throw UnsatisfiedLinkError("$name not found via dlsym(RTLD_DEFAULT)")
        return sym
    }

    /**
     * The main dispatch queue.  `dispatch_get_main_queue()` is an inline function
     * in the libdispatch header so there is no exported symbol for it.  Instead we
     * use the address of the global `_dispatch_main_q` variable directly —
     * `dispatch_sync_f` expects a `dispatch_queue_t`, which is a pointer.
     * `_dispatch_main_q` is a `struct dispatch_queue_s`, so `&_dispatch_main_q`
     * is the correct queue pointer value.
     */
    private val mainQueue: MemorySegment = run {
        findDispatchSymbol("_dispatch_main_q")
    }

    private val dispatchSyncFAddr: MemorySegment = findDispatchSymbol("dispatch_sync_f")
    private val dispatchSyncF = linker.downcallHandle(
        dispatchSyncFAddr,
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
        ),
    )

    private val callbackStub: MemorySegment = linker.upcallStub(
        MethodHandles.lookup().findStatic(
            AppKitMainThread::class.java,
            "runTask",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java),
        ),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS),
        Arena.global(),
    )

    fun <T> runSync(block: () -> T): T {
        if (isMainThread()) {
            return block()
        }

        val id = nextTaskId.getAndIncrement()
        val task = Task(block)
        tasks[id] = task
        return try {
            dispatchSyncF.invokeExact(mainQueue, MemorySegment.ofAddress(id), callbackStub)
            task.result()
        } finally {
            tasks.remove(id)
        }
    }

    private class Task<T>(private val block: () -> T) {
        private val value = AtomicReference<T?>()
        private val failure = AtomicReference<Throwable?>()
        private val completed = AtomicBoolean(false)

        fun run() {
            try {
                value.set(block())
            } catch (t: Throwable) {
                failure.set(t)
            } finally {
                completed.set(true)
            }
        }

        @Suppress("UNCHECKED_CAST")
        fun result(): T {
            check(completed.get()) {
                "dispatch_sync_f returned before executing the AppKit main-thread task"
            }
            failure.get()?.let { throw it }
            return value.get() as T
        }
    }

    @JvmStatic
    private fun runTask(context: MemorySegment) {
        tasks[context.address()]?.run()
    }

    private fun isMainThread(): Boolean =
        ObjCRuntime.msgSend(
            ValueLayout.JAVA_BOOLEAN,
            ObjCRuntime.getClass("NSThread"),
            ObjCRuntime.sel("isMainThread"),
        ) as Boolean
}
