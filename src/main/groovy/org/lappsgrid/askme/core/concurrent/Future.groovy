package org.lappsgrid.askme.core.concurrent

import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 *
 */
class Future implements java.util.concurrent.Future {
    private CountDownLatch latch = new CountDownLatch(1)

    Object value

    @Override
    boolean cancel(boolean mayInterruptIfRunning) {
        return false
    }

    @Override
    boolean isCancelled() {
        return false
    }

    @Override
    boolean isDone() {
        return latch.count == 0
    }

    void set(Object value) {
        this.value = value
        latch.countDown()
    }

    @Override
    Object get() throws InterruptedException, ExecutionException {
        latch.await()
        return value.toString()
    }

//    <T> T get(Class<T> type) throws InterruptedException, ExecutionException {
//        latch.await()
//        return type.asType(value)
//    }


    @Override
    Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (latch.await(timeout, unit)) {
            return value
        }
        return null
    }

//    @Override
//    <T> T get(Class<T> type, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//        if (latch.await(timeout, unit)) {
//            return type.asType(value)
//        }
//        return null
//    }
}
