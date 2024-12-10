public class Deck extends Pack{

    /*
     * Inherits from Pack class
     * Contains cards for the decks players pick up and discard to.
     */

    private final int deckID;

    // Deck constructor
    public Deck() {
        int ID = DeckIDCounter.nextId();
        this.deckID =  ID;
    }

    // Getter that returns the ID of the deck
    public int getDeckID(){
        return deckID;
    }

    // Checks if the cards in the deck are empty
    // Returns a boolean
    public boolean isEmpty(){
        if (super.cards.isEmpty()){
            return true;
        }
        return false;
    }

    // Adds a card to the deck
    @Override
    public synchronized void addCard(Card card){
        cards.add(0,card);
    }
}
