package test.java;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import main.java.Pack;
import main.java.Card;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;


import static org.junit.Assert.*;

public class TestPack {
    private Pack pack;
    private ArrayList<Card> cards = new ArrayList<Card>();

    @Before
    public void setUp (){
        pack = new Pack();

        for(int i = 0; i<16; i++){
            cards.add(new Card(i+1));
        }

        pack.setCards(cards);
    }
    @Test
    public void testInitialisePack(){
        Pack pack = new Pack();
        assertThrows(IllegalArgumentException.class, () -> pack.initialisePack(0));
        assertThrows(IllegalArgumentException.class, () -> pack.initialisePack(-5));
        assertThrows(IllegalArgumentException.class, () -> pack.initialisePack(5));
    }

//    @Test
//    public void testDrawCard(){
//        assertEquals(cards.get(0), pack.drawCard());
//        assertEquals(cards.get(1), pack.drawCard());
//        for (int i =0; i<14; i++){
//            assertEquals(cards.get(i+2), pack.drawCard());
//        }
//        assertNull(pack.drawCard());
//    }

    @After
    public void tearDown(){
        pack = null;
        cards = null;
    }
}
