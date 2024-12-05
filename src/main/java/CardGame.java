package main.java;

import java.util.ArrayList;
import java.util.HashMap;

public class CardGame implements Runnable{

    /*
    *
    *
    *
    */

    private Player[] playerArray;
    private Deck[] deckArray;

    public CardGame(Player[] playerArray, Deck[] deckArray){
        this.playerArray = playerArray;
        this.deckArray = deckArray;
    }

    public Player[] getPlayerArray() {
        return playerArray;
    }

    public void setPlayerArray(Player[] playerArray){
        this.playerArray = playerArray;
    }

    public Deck[] getDeckArray() {
        return deckArray;
    }

    public void setDeckArray(Deck[] deckArray){
        this.deckArray = deckArray;
    }

    public void gameLoop(){

    }

    public void playerTurn(Player player, HashMap<Player, Deck[]> map){
        //if decks are empty, make player wait?
        Hand playerHand = player.getHand();
        playerHand.dealcard(map.get(player));


    }

    public Card pickDrawCard(Player player, Hand hand){

        // if there is a preferred denomination skip,
        // if there is a multiple and a single, choose single
        // if there are 2 multiples, choose smaller
        // if equal multiples pick random
        // if all singles pick random


        ArrayList<Card> cards = hand.getCards();
        Long playerID = player.getPreferredvalue();
        HashMap<Card, Integer> cardCount = new HashMap<>();

        for (int i = 0; i < cards.size();i++){
            Card card = cards.get(i);
            if (cardCount.containsKey(card)) {
                cardCount.put(card, cardCount.get(card) + 1);
            } else {
                cardCount.put(card, 1);
            }
        }

        ArrayList<Card> singleCards = new ArrayList<>();
        ArrayList<Card> multiCards = new ArrayList<>();
        for (Card card : cardCount.keySet()) {
            if (cardCount.get(card) == 1) {
                singleCards.add(card);
            } else {
                multiCards.add(card);
            }
        }

        for (int i = 0; i < cards.size();i++){
            int cardInt = cards.get(i).getIntegervalue();
            if (cardInt == playerID){
                continue;
            } else if (cardCount.get(cards.get(i))){

            }
        }


    }

    public Boolean checkVictoryReached(){

        //has 4 of the same cards

        return null;
    }

    public void checkIllegalMove(){

    }

    public void announceWinner() {

    }

    public HashMap<Player, Deck[]> allocateDeck(Player[] players, Deck[] decks){

        /*
         * Maps an array to a player, where index 0 is the deck to the left of the player, and index 1
         * is the deck to the right of the player
         */
        HashMap<Player, Deck[]> map = new HashMap<>();

        for (int i = 0; i < players.length; i++){
            if (i == 0){
                map.put(players[i], new Deck[]{decks[decks.length - 1],decks[i]});
            }
            else {
                map.put(players[i], new Deck[]{decks[i-1],decks[i]});
            }
        }

        return map;
    }


    @Override
    public void run() {

    }
}
