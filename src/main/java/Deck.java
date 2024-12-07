package main.java;

public class Deck extends Pack implements DeckInterface {

    /*
     *
     *
     *
     */
    private final int deckID;

    public Deck(){
        int Id = DeckIDCounter.nextId();
        this.deckID =  Id;
    }

    public int getDeckID(){
        return deckID;
    }

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
