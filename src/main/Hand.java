package main;

import main.Card;

public class Hand {
    private Card[] cards = new Card[4];

    public Hand(){

    }

    public void setCards(Card[] cards){
        this.cards = cards;
    }

    public Card[] getCards(){
        return cards;
    }
}