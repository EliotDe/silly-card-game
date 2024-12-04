package main.java;

public class Deck extends Pack implements DeckInterface {

    /*
     *
     *
     *
     */

    public Deck(){}

    @Override
    public synchronized void addCard(Card card){
        /*
         *
         *
         *
         */
        cards.add(0,card);
    }
}
