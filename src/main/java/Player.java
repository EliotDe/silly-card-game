package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Player implements PlayerInterface, Runnable {

    /*
     * make threadable
     *
     *
     */

    //setpreferredcard
    private final List<DeckModificationListener> listeners = new ArrayList<>();
    private Hand hand = new Hand();
    private final String username;
    private final int preferredValue;
    private Deck leftDeck;
    private Deck rightDeck;
    private GameLogger logger;

    public static AtomicReference<String> winner = new AtomicReference<>(null);

    public Player(){
        int id = IDCounter.nextId();
        this.preferredValue = id;
        this.username = "Player" + id;
//        this.leftDeck = leftDeck;
//        this.rightDeck = rightDeck;
    }

    public synchronized void setHand(Hand hand){this.hand = hand;}
    public synchronized Hand getHand(){return hand;}
    public synchronized int getPreferredValue(){return preferredValue;}
    public String getUsername(){return username;}
    public synchronized void setLeftDeck(Deck leftDeck){this.leftDeck=leftDeck;}
    public synchronized Deck getLeftDeck(Deck leftDeck){return leftDeck;}
    public synchronized void setRightDeck(Deck rightDeck){this.rightDeck=rightDeck;}
    public synchronized Deck getRightDeck(Deck rightDeck){return rightDeck;}
    public synchronized GameLogger getLogger(){return logger;}

    public synchronized void addDeckModificationListener(DeckModificationListener listener){
        listeners.add(listener);
    }

    private synchronized void fireDeckModificationEvent(Deck deck, String action){
        DeckModificationEvent event = new DeckModificationEvent(this, deck, action);

        for(DeckModificationListener listener : listeners){
            listener.onDeckModified(event);
        }
    }

    @Override
    public void run(){
        logger = new GameLogger(this);

        while(!Thread.currentThread().isInterrupted()){
            //player loop
            synchronized (leftDeck){
                //draw card from left deck & log
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
                System.out.println(username + " wins!");
                winner.set(username);

                Thread.currentThread().interrupt();
            };

            try{
                Thread.sleep(1);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    public Card drawCardFromLeftDeck(){
        Card card = leftDeck.drawCard();
        return card;
    }

    public void discardCardToRightDeck(Card card){
        hand.removeCard(card);
        rightDeck.addCard(card);
    }

    public int getDealDeckID(){
        int ID = leftDeck.getDeckID();
        return ID;
    }

    public Card pickCard(){

        // if there is a preferred denomination skip,
        // if there is a multiple and a single, choose single
        // if there are 2 multiples, choose smaller
        // if equal multiples pick random
        // if all singles pick random


        ArrayList<Card> cards = hand.getCards();
        //int playerPreferredValue = player.getPreferredValue();
        Card preferredCard = null;
        HashMap<Card, Integer> cardCountMap = new HashMap<>();

        for (int i = 0; i < cards.size();i++){
            Card card = cards.get(i);
            if(card.getIntegerValue() == preferredValue){
                preferredCard = card;
            }
            if (cardCountMap.containsKey(card)) {
                cardCountMap.put(card, cardCountMap.get(card) + 1);
            } else {
                cardCountMap.put(card, 1);
            }
        }

        ArrayList<Card> singleCards = new ArrayList<>();
        ArrayList<Card> multiCards = new ArrayList<>();
        for (Card card : cards) {
            if (cardCountMap.get(card) == 1 ) {
                if (card.getIntegerValue() != preferredValue) {
                    singleCards.add(card);
                    return card;
                } else {
                    singleCards.add(card);
                }
            } else {
                multiCards.add(card);
            }
        }

        if(preferredCard != null)
            multiCards.remove(preferredCard);

        Card leastCommonCard = cards.get(0);
        for (int i = 0; i < cards.size();i++){
            Card currentCard = cards.get(i);

            if (cardCountMap.get(currentCard) < cardCountMap.get(leastCommonCard)){
                leastCommonCard = currentCard;
            }
        }


        return leastCommonCard;
    }

    public boolean checkVictory(){
        //boolean victory = false;

        ArrayList<Card> cards = hand.getCards();

        int initialCardValue = cards.get(0).getIntegerValue();
        for(int i =1; i< cards.size(); i++){
            if(cards.get(i).getIntegerValue() != initialCardValue){
                return false;
            }
        }

        return true;

    }


}
