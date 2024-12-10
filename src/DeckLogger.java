import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DeckLogger {

    /*
     * Class which logs the decks final state into a text file
     */

    private final String deckLogFileName;

    // Decklogger constructor class
    public DeckLogger(Deck deck) {
        String currentDir = System.getProperty("user.dir");
        this.deckLogFileName = currentDir + File.separator + "outputs" + File.separator + "deck" + deck.getDeckID() + "_output.txt";
        System.out.println(deckLogFileName);
        logFinalDeck(deck);
    }

    // Logs the final state of the deck at the end of the game
    public synchronized void logFinalDeck(Deck deck) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(deckLogFileName))) {
            writer.write("deck" + deck.getDeckID() + " contents: " + deckToString(deck.getCards()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Takes a deck of cards and turns it into a string which can be outputted onto one line
    public synchronized String deckToString(ArrayList<Card> deck) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deck.size(); i++) {
            sb.append(deck.get(i).getIntegerValue()).append(" ");
        }
        return sb.toString().trim();
    }
}
