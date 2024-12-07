//package test.java;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.InputMismatchException;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.After;
//
//import main.java.Main;
//import main.java.Player;
//import main.java.Card;
//import main.java.Deck;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
//
//public class TestMain {
//    private MockPack pack;
//    Main main = new Main();
//    int playerNumber;
//
//
//    @Before
//    public void setup(){
//        playerNumber = 2;
//        pack = new MockPack();
//        pack.initialisePack(playerNumber*8);
//
//    }

//    @Test
//    public void testBeginGame(){
//        try{
//
//        }catch(InputMismatchException e){
//            System.out.println(e.getMessage());
//        }
//    }

//    @Test
//    public void testDealHands(){
//        try{
//            //Test player hand length
//            Player[] players = main.dealHands(playerNumber, pack);
//            for(int i = 0; i < players.length; i++){
//                assertEquals(players[i].getHand().getCards().size(), 4);
//            }
//            // Test if pack has been handed out correctly
//            Long[] p1Values = new Long[4];
//            Long[] p2Values = new Long[4];
//            for(int i = 0; i < 4; i++){
//                Long cardValueP1 = players[0].getHand().getCards().get(i).getIntegerValue();
//                p1Values[i] = cardValueP1;
//            }
//            for(int i = 0; i < 4; i++){
//                Long cardValueP2 = players[1].getHand().getCards().get(i).getIntegerValue();
//                p2Values[i] = cardValueP2;
//            }
//
////            for(int i = 0; i < 4; i++){
////                int actualValue1 = 16-i;
////                int actualValue2 = 12-i;
////                assertEquals(p1Values[i], actualValue1);
////                assertEquals(p2Values[i], actualValue2);
////            }
//            assertEquals(p1Values[0].longValue(), 16L);
//            assertEquals(p1Values[1].longValue(), 14L);
//            assertEquals(p1Values[2].longValue(), 12L);
//            assertEquals(p1Values[3].longValue(), 10L);
//            assertEquals(p2Values[0].longValue(), 15L);
//            assertEquals(p2Values[1].longValue(), 13L);
//            assertEquals(p2Values[2].longValue(), 11L);
//            assertEquals(p2Values[3].longValue(), 9L);
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
//        Long[] deck1Values = new Long[4];
//        Long[] deck2Values = new Long[4];
//
//        for(int i =0; i<4; i++){
//            Long cardValueD1 = decks[0].getCards().get(i).getIntegerValue();
//            deck1Values[i] = cardValueD1;
//            //System.out.println(cardValueD1);
//        }
//
//        for(int i =0; i<4; i++){
//           Long cardValueD2 = decks[1].getCards().get(i).getIntegerValue();
//            deck2Values[i] = cardValueD2;
//            //System.out.println(cardValueD2);
//        }
//
//        assertEquals(deck1Values[0].longValue(), 10L);
//        assertEquals(deck1Values[1].longValue(), 12L);
//        assertEquals(deck1Values[2].longValue(), 14L);
//        assertEquals(deck1Values[3].longValue(), 16L);
//        assertEquals(deck2Values[0].longValue(), 9L);
//        assertEquals(deck2Values[1].longValue(), 11L);
//        assertEquals(deck2Values[2].longValue(), 13L);
//        assertEquals(deck2Values[3].longValue(), 15L);
//    }
//}
