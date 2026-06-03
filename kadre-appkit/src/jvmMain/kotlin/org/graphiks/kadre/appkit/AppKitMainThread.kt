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

    private val linker = Linker.nativeLinker()
    private val arena = Arena.global()
    private val dispatchLib: SymbolLookup = run {
        val loader = SymbolLookup.loaderLookup()
        if (loader.find("dispatch_get_main_queue").isPresent) loader
        else SymbolLookup.libraryLookup("/usr/lib/libSystem.B.dylib", arena)
    }

    private val dispatchGetMainQueue = linker.downcallHandle(
        dispatchLib.find("dispatch_get_main_queue").orElseThrow {
            UnsatisfiedLinkError("dispatch_get_main_queue not found")
        },
        FunctionDescriptor.of(ValueLayout.ADDRESS),
    )

    private val dispatchSyncF = linker.downcallHandle(
        dispatchLib.find("dispatch_sync_f").orElseThrow {
            UnsatisfiedLinkError("dispatch_sync_f not found")
        },
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
        arena,
    )

    fun <T> runSync(block: () -> T): T {
        if (isMainThread()) {
            return block()
        }

        val id = nextTaskId.getAndIncrement()
        val task = Task(block)
        tasks[id] = task
        return try {
            val queue = dispatchGetMainQueue.invokeExact() as MemorySegment
            dispatchSyncF.invokeExact(queue, MemorySegment.ofAddress(id), callbackStub)
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
