package main.java;

import java.util.concurrent.atomic.AtomicLong;
public class Card {

    /*
     *
     *
     *
     */

//    private CardSuit suit;
//    private CardValue value;
    private final AtomicLong integerValue;

//    public Card(CardSuit suit, CardValue value){
//        if(suit == null || value == null){
//            throw new IllegalArgumentException("Card suit/values cannot be null");
//        }
//        this.suit = suit;
//        this.value = value;
//    }

    public Card(AtomicLong integerValue){
        if(integerValue.get() <= 0){
            throw new IllegalArgumentException("Card value must be a positive integer");
        }
        this.integerValue = integerValue;
    }

//    public CardSuit getSuit(){
//        return this.suit;
//    }

//    public CardValue getValue(){
//        return this.value;
//    }

    public Long getIntegerValue() {
        return integerValue.get();
    }
}
