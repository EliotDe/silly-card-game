package test.java;

import main.java.IDCounter;
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

import static org.junit.Assert.assertEquals;

public class TestDeckIDCounter {

    DeckIDCounter counter;

    @Before
    public void setup(){
        counter = new DeckIDCounter();
    }

    @Test
    public void testNextId(){
        int id1 = counter.nextId();
        int id2 = counter.nextId();
        int id3 = counter.nextId();
        int id4 = counter.nextId();
        assertEquals(4, id4);
    }

}
