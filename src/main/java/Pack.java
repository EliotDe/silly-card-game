package main.java;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Pack implements PackInterface {
    protected ArrayList<Card> cards = new ArrayList<>();
    protected Integer size = cards.size();

    public Pack(){}

    public void initialiseCards(){}

    public synchronized int getPackSize(){
        return this.size;
    }

    public synchronized Card drawCard(){
        return cards.remove(cards.size()-1);
    }

    public synchronized void addCard(Card card){
        cards.add(cards.size()-1, card);
    }

}
