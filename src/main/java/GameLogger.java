package main.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GameLogger {

    private final String playerLogFileName;

    public GameLogger(Player player) {
        String currentDir = System.getProperty("user.dir");
        this.playerLogFileName = currentDir + File.separator+ "Silly Card Game" + File.separator + "player" + player.getPreferredValue() + "_output.txt";
        System.out.println(playerLogFileName);
        initializeLogFile(player);
    }

    // Initializes the log file with the player's initial hand
    private synchronized void initializeLogFile(Player player) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerLogFileName))) {
            writer.write("player " + player.getPreferredValue() + " initial hand: " + handToString(player.getHand()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Logs an action to the file
    public synchronized void logAction(String action) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerLogFileName, true))) {
            writer.write(action);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Converts a player's hand to a string representation
    public synchronized String handToString(Hand hand) {
        ArrayList<Card> cards = hand.getCards();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            sb.append(cards.get(i).getIntegerValue()).append(" ");
        }
        return sb.toString().trim();
    }

    // Logs the final game state for the player
    public synchronized void logGameEnd(Player player, String endState) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerLogFileName, true))) {
            writer.write("player " + player.getPreferredValue() + " " + endState);
            writer.newLine();
            writer.write("player " + player.getPreferredValue() + " final hand: " + handToString(player.getHand()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


