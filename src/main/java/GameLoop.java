package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameLoop implements Runnable{

    /*
     * The class GameLoop handles how the game runs and ends.
     * Creates the players & decks, threads.
     * Logs the winner.
     * Prints the winner.
     */

    private int playerNumber;
    private Player[] playerArray;
    private Deck[] deckArray;
    private ArrayList<Thread> threads = new ArrayList<>();
    private PackInterface pack = new Pack();
    private HashMap<Player, Deck[]> map = new HashMap<>();
    private HashMap<Thread, Player> threadToPlayer = new HashMap<>();
    private String winner;

    // GameLoop constructor
    public GameLoop(){

    }

    //getters and setters
    public int getPlayerNumber(){return this.playerNumber;}
    public void setPlayerNumber(int playerNumber){this.playerNumber = playerNumber;}
    public Player[] getPlayerArray(){return this.playerArray;}
    public void setPlayerArray(Player[] playerArray){this.playerArray = playerArray;}
    public Deck[] getDeckArray(){return this.deckArray;}
    public void setDeckArray(Deck[] deckArray){this.deckArray = deckArray;}
    public ArrayList<Thread> getThreads(){return this.threads;}
    public void setThreads(ArrayList<Thread> threads){this.threads = threads;}
    public PackInterface getPack(){return this.pack;}
    public void setPack(PackInterface pack){this.pack = pack;}
    public HashMap<Player, Deck[]> getMap(){return this.map;}
    public void setMap(HashMap<Player,Deck[]> map){this.map = map;}
    public HashMap<Thread, Player> getThreadToPlayer(){return this.threadToPlayer;}
    public void setThreadToPlayer(HashMap<Thread,Player> threadToPlayer){this.threadToPlayer = threadToPlayer;}
    public String getWinner(){return this.winner;}

    // Main GameLoop function, which runs the game
    public void mainLoop(Scanner scanner){

        threads = new ArrayList<>();

        //Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of players: ");
        playerNumber = Integer.parseInt(scanner.nextLine());

        pack.initialisePack(playerNumber*8);
        //System.out.println(pack);

        this.playerArray = dealHands(playerNumber, pack);
        this.deckArray = dealDecks(playerNumber, pack);

        map = allocateDeck(playerArray, deckArray);
        threadToPlayer = new HashMap<>();

        for(int i =0; i< playerArray.length;i++){
            Player player = playerArray[i];
            player.setLeftDeck(map.get(player)[0]);
            player.setRightDeck(map.get(player)[1]);

            addDeckModificationListeners(player);

            Thread playerThread = new Thread(player);
            threads.add(playerThread);
            playerThread.start();

            threadToPlayer.put(playerThread, player);
        }

        // If there is an interrupt, it interrupts all other threads
        // Notifies them that a player has won and logs finishing text
        boolean gameRunning = true;
        while (gameRunning) {
            for (int i = 0; i < threads.size(); i++) {
                if (! threads.get(i).isAlive() ||  threads.get(i).isInterrupted()) {
                    gameRunning = false;
                    for (int j = 0; j < threads.size(); j++) {
                        Player player = threadToPlayer.get(threads.get(j));
                        GameLogger logger = player.getLogger();
                        if ( threads.get(j).isAlive()) {
                            threads.get(j).interrupt();
                            logger.logAction(Player.winner.get() + " has informed " + player.getUsername() + " that " + Player.winner.get() + " has won");
                            logger.logAction(player.getUsername() + " exits");
                            logger.logAction(player.getUsername() + " hand: " + logger.handToString(player.getHand()));
                        }
                    }
                    break;
                }
            }

            try {
                Thread.sleep(1000); //optimises
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        winner = Player.winner.get();
        System.out.println(winner + " won");
        for (int i = 0; i < deckArray.length; i++) {
            DeckLogger logger = new DeckLogger(deckArray[i]);
        }
    }

    // Deals cards to the player hands from the pack, in a round-robin fashion
    public Player[] dealHands(int playerNumber,  PackInterface pack){

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

    // Deals cards to the decks from the pack, in a round-robin fashion
    public Deck[] dealDecks(int deckNumber, PackInterface pack){

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

    /*
     * This allocates decks (left and right) to players
     * It inputs the array of players and the array of decks
     * It returns a map, where the key is the player, and the value is an array containing the left deck and right deck
     */
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

    // this adds listeners to left and right players
    // ensures a players view of their left and right decks is synchronized with changes made by neighbouring players
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
