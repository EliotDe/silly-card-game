package main.java;

//PLayer cant hold onto non-preferred denomination indefinitely
//should discard them at a certain period of time (should reset after one is gone)


import main.java.Card;

import java.util.ArrayList;

public class Hand {

    /*
     *
     *
     *
     */

//    private Card[] cards = new Card[4];
    private ArrayList<Card> cards= new ArrayList<Card>();

    public Hand(){

    }

    public synchronized void setCards(ArrayList<Card> cards){
        /*
         *
         *
         *
         */
        this.cards = new ArrayList<>(cards);
    }

    public synchronized void dealCard(Card card){
        /*
         *
         *
         *
         */
        cards.add(card);
    }

    public synchronized void removeCard(Card card){
        /*
         *
         *
         *
         */
        System.out.println("Card to remove: " + card);
        System.out.println("Cards in hand: " + cards);

        if(!cards.remove(card)){
            throw new IllegalArgumentException("Card is not in hand!");
        }
    }


    public synchronized ArrayList<Card> getCards(){
        /*
         *
         *
         *
         */
        return cards;
    }
}
