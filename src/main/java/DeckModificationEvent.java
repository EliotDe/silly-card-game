package main.java;

public class DeckModificationEvent {
    private final Player player;
    private final Deck deck;
    private final String action;

    public DeckModificationEvent(Player player, Deck deck, String action){
        this.player = player;
        this.deck = deck;
        this.action = action;
    }

    public synchronized Player getPlayer(){
        return player;
    }

    public synchronized Deck getDeck(){
        return deck;
    }

    public synchronized String getAction() {
        return action;
    }
}
