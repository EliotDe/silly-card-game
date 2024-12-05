package main.java;

import java.util.concurrent.atomic.AtomicLong;
public class IDCounter {

    /*
     *
     *
     *
     */

    private static final AtomicLong counter = new AtomicLong(0);

    public static long nextId() {
        /*
         *
         *
         *
         */
        return counter.incrementAndGet();
    }
}
