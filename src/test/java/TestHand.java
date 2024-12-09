package test.java;

import main.java.Card;
import main.java.Hand;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class TestHand {
    Hand hand;
    ArrayList<Card> cards1;
    ArrayList<Card> cards2;

    @Before
    public void setup(){
        hand = new Hand();
        cards1 = new ArrayList<>();
        cards1.add(new Card(new AtomicInteger(1)));
        cards1.add(new Card(new AtomicInteger(2)));

        cards2 = new ArrayList<>();
        cards2.add(new Card(new AtomicInteger(1))); // These are different cards
        cards2.add(new Card(new AtomicInteger(1))); // These are different cards
        cards2.add(new Card(new AtomicInteger(2)));
    }

    @Test
    public void testHand(){
        //empty
    }

    @Test
    public void testSetCards(){
        //test setting cards from null
        assertEquals(0, hand.getCards().size());
        hand.setCards(cards1);
        assertEquals(cards1.get(0), hand.getCards().get(0));
        assertEquals(cards1.get(1), hand.getCards().get(1));

        //test overwriting current cards
        assertEquals(2, hand.getCards().size());
        hand.setCards(cards2);
        assertEquals(3, hand.getCards().size());
        assertNotEquals(cards1,hand.getCards());
        assertEquals(cards2, hand.getCards());
    }

    @Test
    public void testGetCards(){
//        assertNull(hand.getCards());
        hand.setCards(cards1);
        ArrayList<Card> returnedCards = hand.getCards();
        assertEquals(cards1, returnedCards);
    }

    @Test
    public void testDealCard(){
        hand.setCards(cards1);
        Card card = new Card(new AtomicInteger(3));
        hand.dealCard(card);
        int cardsSize = hand.getCards().size();
        assertEquals(3, cardsSize);
        assertEquals(card, hand.getCards().get(2));
    }

    @Test
    public void testRemoveCard(){
        hand.setCards(cards1);
        //test for absent cards
        IllegalArgumentException eNegative = assertThrows(IllegalArgumentException.class, () ->  hand.removeCard(new Card(new AtomicInteger(10))));
        //test exception message
        assertEquals("Card is not in hand!", eNegative.getMessage());

        assertEquals(2, hand.getCards().size());

        //test removing card from hand
        //Card firstCard = cards1.get(0);
        hand.removeCard(cards1.get(0));
        assertEquals(1, hand.getCards().size());
        assertThrows(IllegalArgumentException.class, () -> hand.removeCard(cards1.get(0)));

        //test that removal is based on card instance not card value
        hand.setCards(cards2);
        hand.removeCard(cards2.get(0));
        //if remove card is based on value, the other card with integer value 0 would be removed (so no exception would be thrown)
        assertThrows(IllegalArgumentException.class, () -> hand.removeCard(cards2.get(0)));
    }

    @After
    public void tearDown(){
        hand = null;
        cards1 = null;
        cards2 = null;
    }
}
