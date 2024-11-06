package main.java;

public class Card {
    private CardSuit suit;
    private CardValue value;
    private int integerValue;

    public Card(CardSuit suit, CardValue value){
        if(suit == null || value == null){
            throw new IllegalArgumentException("Card suit/values cannot be null");
        }
        this.suit = suit;
        this.value = value;
    }

    public Card(int integerValue){
        if(integerValue <= 0){
            throw new IllegalArgumentException("Card value must be a positive integer");
        }
        this.integerValue = integerValue;
    }

    public CardSuit getSuit(){
        return this.suit;
    }

    public CardValue getValue(){
        return this.value;
    }

    public int getIntegerValue() {
        return integerValue;
    }
}