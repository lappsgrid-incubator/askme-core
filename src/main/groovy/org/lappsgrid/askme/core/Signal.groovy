package org.lappsgrid.askme.core

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Wraps a CountDownLatch to provide signal semantics.
 *
 */
class Signal {

    private CountDownLatch latch

    Signal(int n = 1) {
        latch = new CountDownLatch(n)
    }

    void send() {
        latch.countDown()
    }

    void await() {
        latch.await()
    }

    boolean await(long time, TimeUnit unit) {
        latch.await(time, unit)
    }
}
