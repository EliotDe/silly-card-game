import java.util.concurrent.atomic.AtomicInteger;

public class DeckIDCounter {

    /*
     * This class increments an ID for each deck on instantiation of a new deck object.
     */

    private static final AtomicInteger counter = new AtomicInteger(0);

    // Increments the counter so each deck has a unique ID
    public static int nextId() {
        return counter.incrementAndGet();
    }

    //this is only for testing purposes (never used in main code)
    public static void resetCounter(){
        counter.set(0);
    }
}
