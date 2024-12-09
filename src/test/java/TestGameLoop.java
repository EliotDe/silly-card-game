package test.java;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
//import org.junit.AfterAll;
//import org.junit.BeforeAll;

import static org.junit.Assert.*;

import main.java.GameLogger;
import main.java.PackInterface;
import main.java.Player;
import main.java.Hand;
import main.java.Card;
import main.java.Deck;
import main.java.GameLoop;
import main.java.DeckModificationListener;
import main.java.IDCounter;



public class TestGameLoop {
    GameLoop gameLoop;
    Player[] players;
    Deck[] decks;
    Player[] players2;
    Deck[] decks2;
    MockPack pack;
    int playerNumber = 2;

    @Before
    public void setup(){
        //Hands:
        //[1,2,3,4,5]
        //[1,2,2,3,4]
        //[1,2,2,3,3]
        //[1,2,3,3,4]
        //[1,2,3,3,3]
        //[1,3,3,3,3]
        //[2,3,3,3,3]
        gameLoop = new GameLoop();
        pack = new MockPack();
        pack.initialisePack(playerNumber*8);

        Card card1 = new Card(new AtomicInteger(1));
        Card card2 = new Card(new AtomicInteger(2));
        Card card3 = new Card(new AtomicInteger(3));
        Card card4 = new Card(new AtomicInteger(4));
        Card card5 = new Card(new AtomicInteger(5));
        Card[] cardArray1 = {card1,card2,card3,card4,card5};
        Card[] cardArray2 = {card1,card2,card2,card4,card5};
        Card[] cardArray3 = {card1,card2,card2,card3,card3};
        Card[] cardArray4 = {card1,card2,card3,card3,card4};
        Card[] cardArray5 = {card1,card2,card3,card3,card3};
        Card[] cardArray6 = {card1,card3,card3,card3,card3};
        Card[] cardArray7 = {card2,card3,card3,card3,card3};
        Hand hand1 = new Hand();
        for (Card card : cardArray1){
            hand1.dealCard(card);
        }
        Hand hand2 = new Hand();
        for (Card card : cardArray2){
            hand2.dealCard(card);
        }
        Hand hand3 = new Hand();
        for (Card card : cardArray3){
            hand3.dealCard(card);
        }
        Hand hand4 = new Hand();
        for (Card card : cardArray4){
            hand4.dealCard(card);
        }
        Hand hand5 = new Hand();
        for (Card card : cardArray5){
            hand5.dealCard(card);
        }
        Hand hand6 = new Hand();
        for (Card card : cardArray6){
            hand6.dealCard(card);
        }
        Hand hand7 = new Hand();
        for (Card card : cardArray7){
            hand7.dealCard(card);
        }

        //players = new Player[] {new Player(),new Player()};

        players2 = new Player[] {new Player(), new Player(),new Player(), new Player()};
        decks2 = new Deck[] {new Deck(), new Deck(),new Deck(), new Deck()};
    }
//
//    @Test
//    public void testPickCard(){
//        //five cards because player draws a card before picking which card to discard
//        //Player1 [1,2,3,4,5]  -> not 1
//        //Player2 [1,2,3,4,5]  -> not 2
//        //Player1 [1,2,2,3,4]  -> 3 or 4
//        //Player2 [1,1,2,3,4]  -> 3 or 4
//        //Player1 [1,2,2,3,3]  -> 2 or 3
//        //Player2 [1,1,2,3,3]  -> 1 or 3
//        //Player1 [1,2,3,3,4]  -> 2 or 4
//        //Player2 [1,2,3,3,4]  -> 1 or 4
//        //Player1 [1,2,3,3,3]  -> 2
//        //Player2 [1,2,3,3,3]  -> 1
//        //Player1 [1,3,3,3,3]  -> 3
//        //Player2 [2,3,3,3,3]  -> 3
//
//
//    }
//
    @Test
    public void testMainLoop(){
        IDCounter.resetCounter(); //ensures player id's start at zero when instantiated in main loop
        Scanner mockScanner = new Scanner("4\n");
        MockPack mockPack = new MockPack();
        mockPack.initialisePack(32);

        gameLoop.setPack(mockPack);
        gameLoop.mainLoop(mockScanner);

        assertEquals(4, gameLoop.getThreads().size());

        //wait for threads to finish and check that the winner is set
        try{
            for(Thread thread: gameLoop.getThreads()){
                synchronized (thread){
                    thread.wait(1);
                }
                assertFalse(thread.isAlive());
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(gameLoop.getWinner());


    }

    @Test
    public void testDealHands(){
        try{
            //Test player hand length
            Player[] players = gameLoop.dealHands(playerNumber, pack);
            for(int i = 0; i < players.length; i++){
                assertEquals(players[i].getHand().getCards().size(), 4);
            }
            // Test if pack has been handed out correctly
            int[] p1Values = new int[4];
            int[] p2Values = new int[4];
            for(int i = 0; i < 4; i++){
                int cardValueP1 = players[0].getHand().getCards().get(i).getIntegerValue();
                p1Values[i] = cardValueP1;
            }
            for(int i = 0; i < 4; i++){
                int cardValueP2 = players[1].getHand().getCards().get(i).getIntegerValue();
                p2Values[i] = cardValueP2;
            }

            assertEquals(p1Values[0], 16);
            assertEquals(p1Values[1], 14);
            assertEquals(p1Values[2], 12);
            assertEquals(p1Values[3], 10);
            assertEquals(p2Values[0], 15);
            assertEquals(p2Values[1], 13);
            assertEquals(p2Values[2], 11);
            assertEquals(p2Values[3], 9);



        }catch(InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void TestDealDecks(){

        Deck[] decks = gameLoop.dealDecks(playerNumber, pack);
        for(int i =0;i<playerNumber;i++){
            assertEquals(decks[i].getCards().size(), 4);
        }


        int[] deck1Values = new int[4];
        int[] deck2Values = new int[4];

        for(int i =0; i<4; i++){
            int cardValueD1 = decks[0].getCards().get(i).getIntegerValue();
            deck1Values[i] = cardValueD1;
            //System.out.println(cardValueD1);
        }

        for(int i =0; i<4; i++){
            int cardValueD2 = decks[1].getCards().get(i).getIntegerValue();
            deck2Values[i] = cardValueD2;
            //System.out.println(cardValueD2);
        }

        assertEquals(deck1Values[0], 10);
        assertEquals(deck1Values[1], 12);
        assertEquals(deck1Values[2], 14);
        assertEquals(deck1Values[3], 16);
        assertEquals(deck2Values[0], 9);
        assertEquals(deck2Values[1], 11);
        assertEquals(deck2Values[2], 13);
        assertEquals(deck2Values[3], 15);
    }

    @Test
    public void testAllocateDeck(){
        HashMap<Player, Deck[]> map = gameLoop.allocateDeck(players2, decks2);
        Deck[] player1Decks = map.get(players2[0]);
        Deck[] player2Decks = map.get(players2[1]);
        Deck[] player3Decks = map.get(players2[2]);
        Deck[] player4Decks = map.get(players2[3]);

        Deck deck1 = decks2[0];
        Deck deck2 = decks2[1];
        Deck deck3 = decks2[2];
        Deck deck4 = decks2[3];

        //test left decks
        assertEquals(deck4, player1Decks[0]);
        assertEquals(deck1, player2Decks[0]);
        assertEquals(deck2, player3Decks[0]);
        assertEquals(deck3, player4Decks[0]);

        //test right decks
        assertEquals(deck1, player1Decks[1]);
        assertEquals(deck2, player2Decks[1]);
        assertEquals(deck3, player3Decks[1]);
        assertEquals(deck4, player4Decks[1]);

    }

    @Test
    public void testAddDeckModificationListeners(){
        gameLoop.setPlayerArray(players2);

        Player player1 = players2[0];
        Player player2 = players2[1];
        Player player3 = players2[2];
        Player player4 = players2[3];

        //check left and right players listeners is initially empty
        List<DeckModificationListener> p2Listeners = player2.getListeners();
        List<DeckModificationListener> p4Listeners = player4.getListeners();
        assertEquals(0, p2Listeners.size());
        assertEquals(0, p4Listeners.size());

        //check that players to left and right of player1 hava a listener in them
        gameLoop.addDeckModificationListeners(player1);
        p2Listeners = player2.getListeners();
        p4Listeners = player4.getListeners();
        assertEquals(1, p2Listeners.size());
        assertEquals(1, p4Listeners.size());

        //check all other players don't have any listeners
        List<DeckModificationListener> p1Listeners = player1.getListeners();
        List<DeckModificationListener> p3Listeners = player3.getListeners();
        assertEquals(0, p1Listeners.size());
        assertEquals(0, p3Listeners.size());
    }

    @After
    public void tearDown(){
         gameLoop = null;
         players = null;
         decks = null;
         players2 = null;
         decks2 = null;
         pack = null;
         IDCounter.resetCounter();
    }
}
