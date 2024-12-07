package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// ADD CARD TIMER AS PER SPEC

public class CardGame implements Runnable{

    /*
    *
    *
    *
    */

    private Player[] playerArray;
    private Deck[] deckArray;

    public CardGame(){

    }


    public void gameLoop(/*Player[] players, Deck[] decks*/){
        // set up players
        // set up decks
        ArrayList<Thread> threads = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        // Reads
        System.out.println("Enter the number of players: ");
        int playerNumber = Integer.parseInt(scanner.nextLine());

        // Initialises the Pack
        Pack pack = new Pack();
        pack.initialisePack(playerNumber*8);
        //System.out.println(pack);

        this.playerArray = dealHands(playerNumber, pack);
        this.deckArray = dealDecks(playerNumber, pack);

        HashMap<Player, Deck[]> map = allocateDeck(playerArray, deckArray);

        for(int i =0; i< playerArray.length;i++){
            Player player = playerArray[i];
            player.setLeftDeck(map.get(player)[0]);
            player.setRightDeck(map.get(player)[1]);

            addDeckModificationListeners(player);

            Thread playerThread = new Thread(player);
            threads.add(playerThread);
            playerThread.start();
        }

        boolean gameRunning = true;
        while (gameRunning) {
            for (int i = 0; i < threads.size(); i++) {
                // Check if any thread has been interrupted
                if (! threads.get(i).isAlive() ||  threads.get(i).isInterrupted()) {
                    gameRunning = false;
                    // Interrupt all threads
                    for (int j = 0; j < threads.size(); j++) {
                        if ( threads.get(j).isAlive()) {
                            threads.get(j).interrupt();
                        }
                    }
                    break;
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }



        System.out.println("Game over. Winner: " + Player.winner.get());

    }

    private void interruptAllThreads(ArrayList<Thread> threads) {
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).interrupt();
        }
    }

    public Player[] dealHands(int playerNumber,  PackInterface pack){
        /*
         *
         *
         *
         */

        // Creates an Array of players, and hands out cards to their decks
        Player[] players = new Player[playerNumber];
        for (int i = 0; i < playerNumber; i++){
            ArrayList<Card> playerHand = new ArrayList<>();
            Player player = new Player();
            Hand hand = new Hand();
            player.setHand(hand);
            players[i] = player;
        }

        for(int i = 0; i<playerNumber*4;i++){
            int playerIndex = i % playerNumber;
            Hand playerHand = players[playerIndex].getHand();
            playerHand.dealCard(pack.drawCard());
        }
        return players;
    }

    public Deck[] dealDecks(int deckNumber, PackInterface pack){
        /*
         *
         *
         *
         */

        Deck[] decks = new Deck[deckNumber];
        for (int i = 0; i< deckNumber; i++){
            Deck deck = new Deck();
            decks[i] = deck;
        }

        for(int i =0; i<deckNumber*4;i++){
            int deckIndex = i%deckNumber;
            decks[deckIndex].addCard(pack.drawCard());
        }

        return decks;
    }

//    public synchronized void playerTurn(Player player, HashMap<Player, Deck[]> map){
//
//        GameLogger logger = new GameLogger(player);
//
//        //if decks are empty, make player wait?
//        Hand playerHand = player.getHand();
//        Card dealCard = map.get(player)[0].drawCard();
//
//        Long dealCardValue = dealCard.getIntegerValue();
//        Long playerNum = player.getPreferredValue();
//
//        logger.logAction("player " + playerNum + " draws a " + dealCardValue + " from deck " + getDealDeckID(player));
//
//        playerHand.dealCard(dealCard);
//        Card discardCard = pickCard(player, playerHand);
//        map.get(player)[1].addCard(discardCard);
//
//        Long discardCardValue = discardCard.getIntegerValue();
//
//        playerHand.drawCard(discardCard);
//
//        logger.logAction("player " + playerNum + " discards a " + discardCardValue + " to deck " + playerNum);
//        logger.logAction("player " + playerNum + " current hand: " + logger.handToString(playerHand));
//    }

//    public Card pickCard(Player player, Hand hand){
//
//        // if there is a preferred denomination skip,
//        // if there is a multiple and a single, choose single
//        // if there are 2 multiples, choose smaller
//        // if equal multiples pick random
//        // if all singles pick random
//
//
//        ArrayList<Card> cards = hand.getCards();
//        Long playerPreferredValue = player.getPreferredValue();
//        Card playerPreferredCard = null;
//        HashMap<Card, Integer> cardCountMap = new HashMap<>();
//
//        for (int i = 0; i < cards.size();i++){
//            Card card = cards.get(i);
//            if(card.getIntegerValue() == playerPreferredValue){
//                playerPreferredCard = card;
//            }
//            if (cardCountMap.containsKey(card)) {
//                cardCountMap.put(card, cardCountMap.get(card) + 1);
//            } else {
//                cardCountMap.put(card, 1);
//            }
//        }
//
//        ArrayList<Card> singleCards = new ArrayList<>();
//        ArrayList<Card> multiCards = new ArrayList<>();
//        for (Card card : cards) {
//            if (cardCountMap.get(card) == 1 ) {
//                if (card.getIntegerValue() != playerPreferredValue) {
//                    singleCards.add(card);
//                    return card;
//                } else {
//                    singleCards.add(card);
//                }
//            } else {
//                multiCards.add(card);
//            }
//        }
//
//        if(playerPreferredCard != null)
//            multiCards.remove(playerPreferredCard);
//
//        Card leastCommonCard = cards.get(0);
//        for (int i = 0; i < cards.size();i++){
//            Card currentCard = cards.get(i);
//            //int cardValue = currentCard.getIntegervalue();
////            if (cardInt == playerPreferredValue){
////                continue;
//            if (cardCountMap.get(currentCard) < cardCountMap.get(leastCommonCard)){
//                leastCommonCard = currentCard;
//            }
//        }
//
//
//        return leastCommonCard;
//    }

//    public void checkIllegalMove(){
//
//    }

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

//    public Player[] getPlayerArray() {
//        return playerArray;
//    }
//
//    public void setPlayerArray(Player[] playerArray){
//        this.playerArray = playerArray;
//    }
//
//    public Deck[] getDeckArray() {
//        return deckArray;
//    }
//
//    public void setDeckArray(Deck[] deckArray){
//        this.deckArray = deckArray;
//    }
//
//    public Long getDealDeckID(Player player){
//        Long ID = 0L;
//        for (int i = 0; i < playerArray.length; i++){
//            if (player.getPreferredValue() == 1){
//                ID = (long) deckArray.length;
//            }
//            else {
//                ID = player.getPreferredValue() - 1;
//            }
//        }
//        return ID;
//    }
//
//    public Boolean checkVictoryReached(){
//
//        //has 4 of the same cards
//
//        return null;
//    }
//
//    public void announceWinner() {
//
//    }

    public void addDeckModificationListeners(Player currentPlayer){

        int currentPlayerID = currentPlayer.getPreferredValue();
        Player leftPlayer;
        Player rightPlayer;

        if(currentPlayerID==1){
            leftPlayer = playerArray[playerArray.length-1];
        }
        else{
            leftPlayer = playerArray[(int)(currentPlayerID-2)];
        }
        if(currentPlayerID==playerArray.length){
            rightPlayer = playerArray[0];
        }
        else{
            rightPlayer = playerArray[(int) currentPlayerID];
        }

        leftPlayer.addDeckModificationListener(new DeckModificationListener(){
            @Override
            public synchronized void onDeckModified(DeckModificationEvent event){
                if (event.getAction().equals("Discard")){
                    currentPlayer.setLeftDeck(event.getDeck());
                }
            }
        });

        rightPlayer.addDeckModificationListener(new DeckModificationListener(){
            @Override
            public synchronized void onDeckModified(DeckModificationEvent event){
                if (event.getAction().equals("Draw")){
                    currentPlayer.setRightDeck(event.getDeck());
                }
            }
        });

    }

    @Override
    public void run() {

    }
}
