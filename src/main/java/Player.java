package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Player implements PlayerInterface, Runnable {

    /*
     * The class Player is used to instantiate Player objects.
     * Threadable, so many players can do their turns concurrently.
     * Has methods which a player may do, such as draw card.
     */

    private HashMap<Card, Integer> timerMap = new HashMap<>();
    private List<DeckModificationListener> listeners = new ArrayList<>();
    private Hand hand = new Hand();
    private final String username;
    private final int preferredValue;
    private Deck leftDeck;
    private Deck rightDeck;
    private GameLogger logger;

    public static AtomicReference<String> winner = new AtomicReference<>(null);

    // Player constructor
    public Player(){
        int id = IDCounter.nextId();
        this.preferredValue = id;
        this.username = "Player" + id;
//        this.leftDeck = leftDeck;
//        this.rightDeck = rightDeck;
    }

    // getters and setters
    public synchronized void setHand(Hand hand){this.hand = hand;}
    public synchronized Hand getHand(){return hand;}
    public synchronized int getPreferredValue(){return preferredValue;}
    public synchronized String getUsername(){return username;}
    public synchronized void setLeftDeck(Deck leftDeck){this.leftDeck=leftDeck;}
    public synchronized Deck getLeftDeck(){return leftDeck;}
    public synchronized void setRightDeck(Deck rightDeck){this.rightDeck=rightDeck;}
    public synchronized Deck getRightDeck(){return rightDeck;}
    public synchronized GameLogger getLogger(){return logger;}
    public synchronized List<DeckModificationListener> getListeners(){return listeners;}
    public synchronized void setListeners(List<DeckModificationListener> listeners){this.listeners = listeners;}

    // adds a listener to the listeners list
    public synchronized void addDeckModificationListener(DeckModificationListener listener){
        listeners.add(listener);
    }

    // when a player updates a shared deck this method is called
    // passes the event to the left and right players who listen for deckmodification events on this player
    public synchronized void fireDeckModificationEvent(Deck deck, String action){
        DeckModificationEvent event = new DeckModificationEvent(this, deck, action);

        for(DeckModificationListener listener : listeners){
            listener.onDeckModified(event);
        }
    }

    /*
     * contains main functionality for a player thread
     * draws card from left deck, picks card to discard, discards to right deck, checks victory
     */
    @Override
    public void run(){
        logger = new GameLogger(this);

        while(!Thread.currentThread().isInterrupted()){
            //player loop
            synchronized (leftDeck){
                //draw card from left deck & log
                while(leftDeck.isEmpty()){
                    try{
                        leftDeck.wait(10);
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                Card drawnCard = drawCardFromLeftDeck();//leftDeck.drawCard();
                hand.dealCard(drawnCard);
                logger.logAction(this.username + " draws a " + drawnCard.getIntegerValue() + " from deck " + getDealDeckID());
                fireDeckModificationEvent(leftDeck, "draw");
            }
            synchronized (rightDeck){
                //select card to discard
                Card discardCard = pickCard();
                //discard card to right deck & log
                discardCardToRightDeck(discardCard);
                logger.logAction(this.username + " discards a " + discardCard.getIntegerValue() + " to deck " + preferredValue);
                logger.logAction("Hand: " + logger.handToString(hand));
                fireDeckModificationEvent(rightDeck, "discard");
            }
            //check victory
            if(checkVictory()){
                logger.logGameEnd(this, "wins");
                winner.set(username);
                Thread.currentThread().interrupt();
            };

//            try{
//                Thread.sleep(1);
//            }catch (InterruptedException e){
//                Thread.currentThread().interrupt();
//            }
        }
    }

    // draws card
    public Card drawCardFromLeftDeck(){
        Card card = leftDeck.drawCard();
        return card;
    }

    // discards card
    public void discardCardToRightDeck(Card card){
        hand.removeCard(card);
        rightDeck.addCard(card);
    }

    // gets the id of the deck that the player draws a card from
    public int getDealDeckID(){
        int ID = leftDeck.getDeckID();
        return ID;
    }

    // strategically picks the card to discard from player hand
    public Card pickCard(){

        // if there is a preferred denomination skip,
        // if there is a multiple and a single, choose single
        // if there are 2 multiples, choose smaller
        // if equal multiples pick random
        // if all singles pick random

        ArrayList<Card> cards = hand.getCards();
        Card leastCommonCard = cards.get(0);
        //int playerPreferredValue = preferredValue;
        ArrayList<Card> preferredCards= new ArrayList<>();
        HashMap<Integer, Integer> cardCountMap = new HashMap<>();

        for (int i = 0; i < cards.size();i++){
            Card card = cards.get(i);
            if(card.getIntegerValue() == preferredValue){
                preferredCards.add(card);
            }
            else{
                leastCommonCard = card;
            }
            if (cardCountMap.containsKey(card.getIntegerValue())) {
                cardCountMap.put(card.getIntegerValue(), cardCountMap.get(card.getIntegerValue()) + 1);
            } else {
                cardCountMap.put(card.getIntegerValue(), 1);
            }
        }


//        ArrayList<Card> singleCards = new ArrayList<>();
//        ArrayList<Card> multiCards = new ArrayList<>();
//        for (Card card : cards) {
//            if (cardCountMap.get(card.getIntegerValue()) == 1 ) {
//                if (card.getIntegerValue() != preferredValue) {
//                    singleCards.add(card);
//                    return card;
//                } else {
//                    singleCards.add(card);
//                }
//            } else {
//                multiCards.add(card);
//            }
//        }

//        if(!preferredCards.isEmpty()) {
////            for (int i = preferredCards.size(); i > 0; i--) {
////                multiCards.remove(preferredCards.get(0));
////            }
//            for(Card card: preferredCards){
//                cards.remove(card);
//            }
//        }
        //Card leastCommonCard = ;
        for (int i = 0; i < cards.size();i++){
            Card currentCard = cards.get(i);

            if(preferredCards.contains(currentCard)){
               continue;
            }
            else if (cardCountMap.get(currentCard.getIntegerValue()) < cardCountMap.get(leastCommonCard.getIntegerValue())){
                leastCommonCard = currentCard;
            }
        }

//        // Increment the turn count for each card in the player's hand
//        for (Card card : hand.getCards()) {
//            timerMap.put(card, timerMap.getOrDefault(card, 0) + 1);
//        }
//
//        // Remove cards that are no longer in the player's hand
//        timerMap.keySet().removeIf(card -> !hand.contains(card));
//
//        timerMap.put(card, 0); // Timer for the cards (5 turns before a player has to discard a non-preferred card)


        return leastCommonCard;
    }

    // checks if all cards in the hand are the same
    public boolean checkVictory(){
        //boolean victory = false;

        ArrayList<Card> cards = hand.getCards();

        int initialCardValue = cards.get(0).getIntegerValue();
        for(int i = 1; i < cards.size(); i++){
            if(cards.get(i).getIntegerValue() != initialCardValue){
                return false;
            }
        }

        return true;
    }
}
