package main.java;

public class DeckModificationEvent {

    /*
     * Event which is fired when the deck is modified.
     * Ensures that player decks are synchronised.
     */

    private final Player player;
    private final Deck deck;
    private final String action;

    // DeckModificationEvent constructor
    public DeckModificationEvent(Player player, Deck deck, String action){
        this.player = player;
        this.deck = deck;
        this.action = action;
    }

    // Getter which gets the player object associated with the action and deck
    public synchronized Player getPlayer(){
        return player;
    }

    // Getter which gets the deck object associated with the player and the action
    public synchronized Deck getDeck(){
        return deck;
    }

    // Getter which gets the action string associated with the player and the deck
    public synchronized String getAction() {
        return action;
    }
}
