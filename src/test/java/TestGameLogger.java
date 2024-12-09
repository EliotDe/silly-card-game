package test.java;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
//import org.junit.AfterAll;
//import org.junit.BeforeAll;

import static org.junit.Assert.assertEquals;
import java.lang.reflect.Method;

import main.java.GameLogger;
import main.java.Player;
import main.java.Hand;
import main.java.Card;
import main.java.Deck;

public class TestGameLogger {
    GameLogger logger;
    GameLogger logger2;
    Player player;
    Hand hand;
    Card card;
    Deck deck;
    AtomicInteger x = new AtomicInteger(1);

    @Before
    public void setup(){
        player = new Player();
        deck = new Deck();
        logger = new GameLogger(player);
        hand = new Hand();
        card = new Card(x);
    }

    @Test
    public void testInitializeLogFile(){
        //use reflection this is a private method
        try {
            Method initialiseLogFileMethod = GameLogger.class.getDeclaredMethod("initialiseLogFile", Player.class);

            initialiseLogFileMethod.setAccessible(true);

            // Invoke the private method with a test action
            initialiseLogFileMethod.invoke(logger, player);

            String currentDir = System.getProperty("user.dir");
            String filePath = currentDir + File.separator+ "Silly Card Game" + File.separator + "outputs" + File.separator + "player" + player.getPreferredValue() + "_output.txt";

            StringBuilder fileContent = new StringBuilder();

            try (Scanner scanner = new Scanner(new File(filePath))) {
                while (scanner.hasNextLine()) {
                    fileContent.append(scanner.nextLine()).append("\n");
                }
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

            assertEquals("Player" + player.getPreferredValue() + " initial hand:\s\n", fileContent.toString());
        } catch(InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }

    @Test
    public void testLogAction(){

        logger.logAction("Hello World!");

        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator+ "Silly Card Game" + File.separator + "outputs" + File.separator + "player" + player.getPreferredValue() + "_output.txt";

        StringBuilder fileContent = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine()).append("\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        assertEquals("Player" + player.getPreferredValue() + " initial hand:\s\n" + "Hello World!\n", fileContent.toString());
    }

    @Test
    public void testHandToString(){
        hand.dealCard(card);
        hand.dealCard(card);
        hand.dealCard(card);
        hand.dealCard(card);

        assertEquals("1 1 1 1", logger.handToString(hand));
    }

    @Test
    public void testLogGameEnd(){

        logger.logGameEnd(player, "won");

        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator+ "Silly Card Game" + File.separator + "outputs" + File.separator + "player" + player.getPreferredValue() + "_output.txt";

        StringBuilder fileContent = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine()).append("\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        assertEquals("Player" + player.getPreferredValue() + " initial hand:\s\n" + "Player" + player.getPreferredValue() + " won\n" + "Player" + player.getPreferredValue() + " exits\n" + "Player" + player.getPreferredValue() + " final hand:\s\n",
                fileContent.toString());
    }

    @After
    public void tearDown(){

    }
}
