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

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

import main.java.DeckLogger;
import main.java.Player;
import main.java.Hand;

import static org.junit.Assert.*;

public class TestDeckLogger {
    DeckLogger logger;
    Player player;
    Hand hand;
    Card card;
    Deck deck;
    AtomicInteger x = new AtomicInteger(1);

    @Before
    public void setup(){
        player = new Player();
        deck = new Deck();
        logger = new DeckLogger(deck);
        hand = new Hand();
        card = new Card(x);
    }

    @Test
    public void testLogFinalDeck(){

        deck.addCard(card);
        deck.addCard(card);
        deck.addCard(card);
        deck.addCard(card);

        logger.logFinalDeck(deck);

        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator+ "Silly Card Game" + File.separator + "outputs" + File.separator + "deck" + deck.getDeckID() + "_output.txt";

        StringBuilder fileContent = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine()).append(" ");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        assertEquals("deck" + deck.getDeckID() + " contents: " + logger.deckToString(deck.getCards()) + " ", fileContent.toString());
    }

    @Test
    public void testDeckToString(){
        deck.addCard(card);
        deck.addCard(card);
        deck.addCard(card);
        deck.addCard(card);

        assertEquals("1 1 1 1", logger.deckToString(deck.getCards()));
    }

    @After
    public void tearDown(){

    }
}
