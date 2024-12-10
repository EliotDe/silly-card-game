import java.util.ArrayList;

public class Hand {

    /*
     * This class holds the cards in each player's hands.
     * Has methods for the player to remove or add a card to their hands.
     */

    private ArrayList<Card> cards= new ArrayList<Card>();

    // Hand constructor
    public Hand(){

    }

    // Setter to set the cards in the player's hand
    public synchronized void setCards(ArrayList<Card> cards){
        this.cards = new ArrayList<>(cards);
    }

    // Adds a card to the current player's hand
    public synchronized void dealCard(Card card){
        cards.add(card);
    }

    // Removes as card from the hand
    public synchronized void removeCard(Card card){
        if(!cards.remove(card)){
            throw new IllegalArgumentException("Card is not in hand!");
        }
    }

    // Returns the cards in the player's hand
    public synchronized ArrayList<Card> getCards(){
        return cards;
    }
}
