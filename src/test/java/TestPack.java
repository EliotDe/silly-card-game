package test.java;

import org.junit.Test;

import main.java.Card;
import main.java.Pack;
import main.java.Deck;
import main.java.DeckIDCounter;

import org.junit.Before;
import org.junit.After;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class TestPack {
    Pack pack;
    Pack pack2;
    Pack emptyPack;
    ArrayList<Card> cards;

    String testPath;
    ArrayList<String> fileLines;
    //ByteArrayInputStream inputStream;

    @Before
    public void setUp (){
        pack = new Pack();
        pack2 = new Pack();
        emptyPack = new Pack();
        cards = new ArrayList<>();

        String currentDir = System.getProperty("user.dir");
        testPath = currentDir + File.separator+ "Silly Card Game" + File.separator+ "src"+ File.separator+"test"+File.separator+"resources" + File.separator + "testPack1.txt";
        //inputStream = new ByteArrayInputStream(testPath.getBytes());
        for(int i = 0; i<16; i++){
            cards.add(new Card(new AtomicInteger(i+1)));
        }
        pack.setCards(cards);
    }

    @Test
    public void testInitialisePack(){
        //Pack pack = new Pack();
        IllegalArgumentException eZero = assertThrows(IllegalArgumentException.class, () -> pack2.initialisePack(0));
        IllegalArgumentException eNegative = assertThrows(IllegalArgumentException.class, () -> pack2.initialisePack(-5));
        IllegalArgumentException eNotMultiple = assertThrows(IllegalArgumentException.class, () -> pack2.initialisePack(5));
        assertEquals("Card number must be a positive multiple of eight", eZero.getMessage());
        assertEquals("Card number must be a positive multiple of eight", eNegative.getMessage());
        assertEquals("Card number must be a positive multiple of eight", eNotMultiple.getMessage());
    }

//    @Test
//    public void testGetFilePathFromUser(){
//
//    }

    @Test
    public void testReadFile(){
        try{
            ArrayList<String> lines = pack2.readFile(testPath);
            assertEquals("1", lines.get(0));
            assertEquals("2", lines.get(1));
            assertEquals("3", lines.get(2));
            assertEquals("4", lines.get(3));
            assertEquals("5", lines.get(4));
            assertEquals("16", lines.get(5));
            assertEquals("8", lines.get(6));
            assertEquals("9", lines.get(7));
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGetPackSize(){
        //Test initial pack size
        assertEquals(16, pack.getPackSize());

        //Test pack size upon alterations
        pack.drawCard();
        assertEquals(15, pack.getPackSize());
    }

    @Test
    public void testGetCards(){
        assertEquals(cards, pack.getCards());
    }

    @Test
    public void testSetCards(){
        assertEquals(0, emptyPack.getPackSize());

        emptyPack.setCards(cards);
        assertEquals(cards, emptyPack.getCards());
    }

    @Test
    public void testDrawCard(){
        //test draw card on empty pack
        IllegalArgumentException emptyPackException = assertThrows(IllegalArgumentException.class, () -> emptyPack.drawCard());
        assertEquals("The pack is empty!", emptyPackException.getMessage());

        //test draw card on pack with cards
        assertEquals(16, pack.getPackSize());
        assertEquals(cards.get(cards.size()-1), pack.drawCard());
        assertEquals(15, pack.getPackSize());
    }

    @Test
    public void testAddCard(){

    }


    @After
    public void tearDown(){
        pack = null;
        emptyPack = null;
        cards = null;
    }
}
