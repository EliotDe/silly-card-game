package test.java;

import main.java.Deck;
import org.junit.Test;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import main.java.CardGame;
import main.java.Card;
import main.java.Hand;
import main.java.Player;

import static org.junit.Assert.assertEquals;


public class TestCardGame {
//    Player[] players;
//    MockPack pack;
//    int playerNumber = 2;
//
//    @Before
//    public void setup(){
//        //Hands:
//        //[1,2,3,4,5]
//        //[1,2,2,3,4]
//        //[1,2,2,3,3]
//        //[1,2,3,3,4]
//        //[1,2,3,3,3]
//        //[1,3,3,3,3]
//        //[2,3,3,3,3]
//        pack = new MockPack();
//        pack.initialisePack(playerNumber*8);
//
//        Card card1 = new Card(new AtomicInteger(1));
//        Card card2 = new Card(new AtomicInteger(2));
//        Card card3 = new Card(new AtomicInteger(3));
//        Card card4 = new Card(new AtomicInteger(4));
//        Card card5 = new Card(new AtomicInteger(5));
//        Card[] cardArray1 = {card1,card2,card3,card4,card5};
//        Card[] cardArray2 = {card1,card2,card2,card4,card5};
//        Card[] cardArray3 = {card1,card2,card2,card3,card3};
//        Card[] cardArray4 = {card1,card2,card3,card3,card4};
//        Card[] cardArray5 = {card1,card2,card3,card3,card3};
//        Card[] cardArray6 = {card1,card3,card3,card3,card3};
//        Card[] cardArray7 = {card2,card3,card3,card3,card3};
//        Hand hand1 = new Hand();
//        for (Card card : cardArray1){
//            hand1.dealCard(card);
//        }
//        Hand hand2 = new Hand();
//        for (Card card : cardArray2){
//            hand2.dealCard(card);
//        }
//        Hand hand3 = new Hand();
//        for (Card card : cardArray3){
//            hand3.dealCard(card);
//        }
//        Hand hand4 = new Hand();
//        for (Card card : cardArray4){
//            hand4.dealCard(card);
//        }
//        Hand hand5 = new Hand();
//        for (Card card : cardArray5){
//            hand5.dealCard(card);
//        }
//        Hand hand6 = new Hand();
//        for (Card card : cardArray6){
//            hand6.dealCard(card);
//        }
//        Hand hand7 = new Hand();
//        for (Card card : cardArray7){
//            hand7.dealCard(card);
//        }
//
//        players = new Player[] {new Player(),new Player()};
//
//
//    }
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
//    @Test
//    public void testDealHands(){
//        try{
//            //Test player hand length
//            Player[] players = CardGame.dealHands(playerNumber, pack);
//            for(int i = 0; i < players.length; i++){
//                assertEquals(players[i].getHand().getCards().size(), 4);
//            }
//            // Test if pack has been handed out correctly
//            int[] p1Values = new int[4];
//            int[] p2Values = new int[4];
//            for(int i = 0; i < 4; i++){
//                intcardValueP1 = players[0].getHand().getCards().get(i).getIntegerValue();
//                p1Values[i] = cardValueP1;
//            }
//            for(int i = 0; i < 4; i++){
//                int cardValueP2 = players[1].getHand().getCards().get(i).getIntegerValue();
//                p2Values[i] = cardValueP2;
//            }
//
////            for(int i = 0; i < 4; i++){
////                int actualValue1 = 16-i;
////                int actualValue2 = 12-i;
////                assertEquals(p1Values[i], actualValue1);
////                assertEquals(p2Values[i], actualValue2);
////            }
//            assertEquals(p1Values[0], 16);
//            assertEquals(p1Values[1], 14);
//            assertEquals(p1Values[2], 12);
//            assertEquals(p1Values[3], 10);
//            assertEquals(p2Values[0], 15);
//            assertEquals(p2Values[1], 13);
//            assertEquals(p2Values[2], 11);
//            assertEquals(p2Values[3], 9);
//
////            ArrayList<Card> cards = pack.getCards();
////
////            for(int i=0;i<cards.size();i++){
////                System.out.println(cards.get(i).getIntegerValue());
////            }
////            System.out.println("\n\n\n");
//
//
//        }catch(InputMismatchException e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Test
//    public void TestDealDecks(){
//        //ArrayList<Card> cards = pack.getCards();
//
////        for(int i=0;i<cards.size();i++){
////            System.out.println(cards.get(i).getIntegerValue());
////        }
////        System.out.println("\n\n\n");
////
////        assertEquals(pack.getCards().size(), 16);
//
//
//        Deck[] decks = main.dealDecks(playerNumber, pack);
//        for(int i =0;i<playerNumber;i++){
//            assertEquals(decks[i].getCards().size(), 4);
//        }
//
////        assertEquals(pack.getCards().size(), 8);
////        cards = pack.getCards();
////
////        for(int i=0;i<cards.size();i++){
////            System.out.println(cards.get(i).getIntegerValue());
////        }
//
//        int[] deck1Values = new int[4];
//        int[] deck2Values = new int[4];
//
//        for(int i =0; i<4; i++){
//            int cardValueD1 = decks[0].getCards().get(i).getIntegerValue();
//            deck1Values[i] = cardValueD1;
//            //System.out.println(cardValueD1);
//        }
//
//        for(int i =0; i<4; i++){
//            int cardValueD2 = decks[1].getCards().get(i).getIntegerValue();
//            deck2Values[i] = cardValueD2;
//            //System.out.println(cardValueD2);
//        }
//
//        assertEquals(deck1Values[0], 10);
//        assertEquals(deck1Values[1], 12);
//        assertEquals(deck1Values[2], 14);
//        assertEquals(deck1Values[3], 16);
//        assertEquals(deck2Values[0], 9);
//        assertEquals(deck2Values[1], 11);
//        assertEquals(deck2Values[2], 13);
//        assertEquals(deck2Values[3], 15);
//    }
}
