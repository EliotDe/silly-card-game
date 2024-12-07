package main.java;

import java.util.concurrent.atomic.AtomicInteger;

public class DeckIDCounter {

    private static final AtomicInteger counter = new AtomicInteger(0);

    public static int nextId() {
        /*
         *
         *
         *
         */
        return counter.incrementAndGet();
    }
}
