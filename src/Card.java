import java.util.concurrent.atomic.AtomicInteger;
public class Card {

    /*
     * This class is used to create individual card objects for each player
     * using class instantiation.
     */

    private final AtomicInteger integerValue;

    // Card constructor
    public Card(AtomicInteger integerValue){
        if(integerValue.get() <= 0){
            throw new IllegalArgumentException("Card value must be a positive integer");
        }
        this.integerValue = integerValue;
    }

    // Returns the integer value of the card
    public int getIntegerValue() {
        return integerValue.get();
    }
}
