package test.java;

import main.java.Card;
//import main.java.CardSuit;
//import main.java.CardValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCard {
    Card card;

    @Before
    public void setUp(){
        card = new Card(new AtomicInteger(1));
    }

    @Test
    public void testCard(){
        IllegalArgumentException eZero = assertThrows(IllegalArgumentException.class, () -> new Card(new AtomicInteger(0)));
        IllegalArgumentException eNegative = assertThrows(IllegalArgumentException.class, () -> new Card(new AtomicInteger(-1)));

        assertEquals("Card value must be a positive integer", eZero.getMessage());
        assertEquals("Card value must be a positive integer", eNegative.getMessage());
    }

    @Test
    public void testGetIntegerValue(){
        //Card card1  = new Card(new AtomicInteger(1));
        assertEquals(1, card.getIntegerValue());
    }

    @After
    public void tearDown(){
        card = null;
    }
}
