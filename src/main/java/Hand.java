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

    public void setCards(ArrayList<Card> cards){
        /*
         *
         *
         *
         */
        this.cards = cards;
    }

    public void dealCard(Card card){
        /*
         *
         *
         *
         */
        cards.add(card);
    }

    public Card drawCard(Card card){
        /*
         *
         *
         *
         */
        cards.remove(card);
    }


    public ArrayList<Card> getCards(){
        /*
         *
         *
         *
         */
        return cards;
    }
}
