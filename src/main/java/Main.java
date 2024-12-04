    /*TODO
     *MAKE COMMENTS
     *UPDATE README
     *threading
     */


package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Reads
        System.out.println("Enter the number of players: ");
        int playerNumber = Integer.parseInt(scanner.nextLine());

        // Initialises the Pack
        Pack pack = new Pack();
        pack.initialisePack(playerNumber*8);
        //System.out.println(pack);

        Player[] players = dealHands(playerNumber, pack);
        Deck[] decks = dealDecks(playerNumber, pack);


    }

    public static Player[] dealHands(int playerNumber,  PackInterface pack){

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
//            for (int j = 0; j < 4; j++){
//                playerHand.add(pack.drawCard());
//            }
            Hand hand = new Hand();
//            hand.setCards(playerHand);
            player.setHand(hand);
            players[i] = player;
        };

        for(int i = 0; i<playerNumber*4;i++){
            int playerIndex = i % playerNumber;
            Hand playerHand = players[playerIndex].getHand();
            playerHand.dealCard(pack.drawCard());
        }
        return players;
    }

    public static Deck[] dealDecks(int deckNumber, PackInterface pack){

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


}