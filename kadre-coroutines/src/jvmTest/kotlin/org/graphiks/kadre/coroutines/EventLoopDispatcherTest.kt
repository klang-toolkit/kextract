package org.graphiks.kadre.coroutines

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EventLoopDispatcherTest {

    @Test
    fun dispatchedWorkRunsOnlyWhenPumped() {
        val dispatcher = EventLoopDispatcher()
        var ran = false
        CoroutineScope(dispatcher).launch { ran = true }

        assertFalse(ran, "work must not run before pump()")
        dispatcher.pump()
        assertTrue(ran, "work runs on pump() on the calling thread")
    }

    @Test
    fun delayResumesAfterItsDeadline() {
        val dispatcher = EventLoopDispatcher()
        var done = false
        CoroutineScope(dispatcher).launch {
            delay(50)
            done = true
        }

        dispatcher.pump() // runs until the coroutine suspends on delay()
        assertFalse(done, "delay() must not have elapsed yet")

        Thread.sleep(80)
        dispatcher.pump() // the timed continuation is now due
        assertTrue(done, "delay() resumes after its deadline on pump()")
    }
}
