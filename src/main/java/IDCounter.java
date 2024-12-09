package main.java;

import java.util.concurrent.atomic.AtomicInteger;
public class IDCounter {

    /*
     * This class increments an ID for each player on instantiation of a new player object.
     */

    private static final AtomicInteger counter = new AtomicInteger(0);

    // Increments the counter so each player has a unique ID
    public static int nextId() {
        return counter.incrementAndGet();
    }

    //this method is only for testing purposes
    public static void resetCounter(){
        counter.set(0);
    }

}
