package test.java;

import org.junit.Test;

import main.java.Card;
import main.java.Pack;
import main.java.Deck;
import main.java.DeckIDCounter;
import main.java.IDCounter;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;


public class TestIDCounter {

    IDCounter counter;

    @Before
    public void setup(){
        counter = new IDCounter();
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