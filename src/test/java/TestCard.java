package test.java;

import main.java.Card;
//import main.java.CardSuit;
//import main.java.CardValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import java.util.concurrent.atomic.AtomicLong;

public class TestCard {

    @Test
    public void testCard(){
//        assertThrows(IllegalArgumentException.class, () -> new Card(null,null));
//        assertThrows(IllegalArgumentException.class, () -> new Card(CardSuit.CLUB,null));
//        assertThrows(IllegalArgumentException.class, () -> new Card(null,CardValue.ACE));
//        assertThrows(IllegalArgumentException.class, () -> new Card(null));
        assertThrows(IllegalArgumentException.class, () -> new Card(new AtomicLong(0)));
        assertThrows(IllegalArgumentException.class, () -> new Card(new AtomicLong(-1)));
    }

//    @Test
//    public void testGetSuit(){
//        Card card1  = new Card(CardSuit.CLUB, CardValue.ACE);
//        Card card2  = new Card(CardSuit.SPADE, CardValue.ACE);
//        Card card3  = new Card(CardSuit.DIAMOND, CardValue.ACE);
//        Card card4  = new Card(CardSuit.HEART, CardValue.ACE);
//
//        assertEquals(CardSuit.CLUB, card1.getSuit());
//        assertEquals(CardSuit.SPADE, card2.getSuit());
//        assertEquals(CardSuit.DIAMOND, card3.getSuit());
//        assertEquals(CardSuit.HEART, card4.getSuit());
//    }
//
//    @Test
//    public void testGetValue(){
//        Card card1  = new Card(CardSuit.HEART, CardValue.ACE);
//        Card card2  = new Card(CardSuit.HEART, CardValue.TWO);
//        Card card3  = new Card(CardSuit.HEART, CardValue.THREE);
//        Card card4  = new Card(CardSuit.HEART, CardValue.FOUR);
//        Card card5  = new Card(CardSuit.HEART, CardValue.FIVE);
//        Card card6  = new Card(CardSuit.HEART, CardValue.SIX);
//        Card card7  = new Card(CardSuit.HEART, CardValue.SEVEN);
//        Card card8  = new Card(CardSuit.HEART, CardValue.EIGHT);
//        Card card9  = new Card(CardSuit.HEART, CardValue.NINE);
//        Card card10  = new Card(CardSuit.HEART, CardValue.JACK);
//        Card card11  = new Card(CardSuit.HEART, CardValue.QUEEN);
//        Card card12  = new Card(CardSuit.HEART, CardValue.KING);
//
//        assertEquals(CardValue.ACE, card1.getValue());
//        assertEquals(CardValue.TWO, card2.getValue());
//        assertEquals(CardValue.THREE, card3.getValue());
//        assertEquals(CardValue.FOUR, card4.getValue());
//        assertEquals(CardValue.FIVE, card5.getValue());
//        assertEquals(CardValue.SIX, card6.getValue());
//        assertEquals(CardValue.SEVEN, card7.getValue());
//        assertEquals(CardValue.EIGHT, card8.getValue());
//        assertEquals(CardValue.NINE, card9.getValue());
//        assertEquals(CardValue.JACK, card10.getValue());
//        assertEquals(CardValue.QUEEN, card11.getValue());
//        assertEquals(CardValue.KING, card12.getValue());
//    }

    @Test
    public void testGetIntegerValue(){
        Card card1  = new Card(new AtomicLong(1));
        assertEquals(1, card1.getIntegerValue().longValue());
    }
}
