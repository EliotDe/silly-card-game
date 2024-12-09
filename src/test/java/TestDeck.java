package test.java;

import org.junit.Test;

import main.java.Card;
import main.java.Pack;
import main.java.Deck;
import main.java.DeckIDCounter;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;



import static org.junit.Assert.*;

public class TestDeck {
    Deck deck1;
    Deck deck2;
    Deck emptyDeck;
    ArrayList<Card> cards1;
    ArrayList<Card> cards2;

    @Before
    public  void setup(){
        deck1 = new Deck();
        cards1 = new ArrayList<>();
        cards1.add(new Card(new AtomicInteger(1)));
        cards1.add(new Card(new AtomicInteger(2)));
        cards1.add(new Card(new AtomicInteger(3)));
        cards1.add(new Card(new AtomicInteger(4)));
        deck1.setCards(cards1);
        deck2 = new Deck();
        cards2 = new ArrayList<>();
        cards2.add(new Card(new AtomicInteger(5)));
        cards2.add(new Card(new AtomicInteger(6)));
        cards2.add(new Card(new AtomicInteger(7)));
        cards2.add(new Card(new AtomicInteger(8)));
        deck2.setCards(cards2);
        emptyDeck = new Deck();
    }

    @Test
    public void testGetDeckID(){
        int ID1 = deck1.getDeckID();
        int ID2 = deck2.getDeckID();
        int ID3 = emptyDeck.getDeckID();
        assertEquals(1, ID1);
        assertEquals(2,ID2);
        assertEquals(3,ID3);
    }

    @Test
    public void testAddCard(){
        assertEquals(4, deck1.getCards().size());

        //Test card properly added to top of deck
        Card newCard = new Card(new AtomicInteger(10));
        deck1.addCard(newCard);
        assertEquals(5, deck1.getCards().size());
        assertEquals(newCard, deck1.getCards().get(0));
    }

    @Test
    public void testIsEmpty(){
        assertEquals(0, emptyDeck.getCards().size());
        assertTrue(emptyDeck.isEmpty());
        assertFalse(deck1.isEmpty());
        assertFalse(deck2.isEmpty());
    }

    @After
    public  void tearDown(){
        deck1 = null;
        deck2 = null;
        emptyDeck = null;
        cards1 = null;
        cards2 = null;
        DeckIDCounter.resetCounter();
    }
}
