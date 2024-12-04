package main.java;

public class IDCounter {

    /*
     *
     *
     *
     */

    private static long counter = 0;

    public static synchronized long nextId() {
        /*
         *
         *
         *
         */
        return ++counter;
    }
}
