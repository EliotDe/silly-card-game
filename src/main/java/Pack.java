package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class Pack implements PackInterface {

    /*
     * This class is the initial pack created using the input file from the user.
     * Has methods to hand out cards to each player and each deck.
     */

    protected ArrayList<Card> cards = new ArrayList<>();

    // Pack Constructor
    public Pack(){}

    // Takes an input of how many cards are supposed to exist
    // Uses the players input file to create a pack
    @Override
    public void initialisePack(int cardNumber){
//        Scanner scanner = new Scanner(System.in);
        boolean positive = cardNumber > 0;
        boolean multipleOfEight = cardNumber % 8 ==0;

        if (!(positive && multipleOfEight)){
            throw new IllegalArgumentException("Card number must be a positive multiple of eight");
        }

        try {
            int lineCount = 0;
            int currentData = 1; // To check if the current number from the file is positive
            while (lineCount != cardNumber && currentData > 0){
                String filePath = getFilePathFromUser();
                ArrayList<String> lines = readFile(filePath);
                lineCount = 0;
                for (String line: lines){
                    currentData = Integer.parseInt(line);
                    addCard(new Card(new AtomicInteger(currentData)));
                    lineCount++;
                }
                if (lineCount != cardNumber){
                    System.out.println("Error, pack line count is not proportional to player number.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred: " + e);
        }
    }

    // Asks for user input, for the file path of the pack to load
    @Override
    public String getFilePathFromUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the location of the pack to load: ");
        return scanner.nextLine();
    }

    // Reads the file from the user input, and puts it into an arraylist of lines
    @Override
    public ArrayList<String> readFile(String filePath) throws FileNotFoundException{
        ArrayList<String> lines = new ArrayList<>();
        File packFile = new File(filePath);
        Scanner scanner = new Scanner(packFile);
        while(scanner.hasNextLine()){
            lines.add(scanner.nextLine());
        }
        scanner.close();
        return lines;
    }
    // Getter that returns the size of the pack
    @Override
    public synchronized int getPackSize(){
        return cards.size();
    }

    // Getter that returns the cards in the pack
    @Override
    public synchronized ArrayList<Card> getCards(){
        return cards;
    }

    // Setter that sets the cards in the pack
    @Override
    public synchronized void setCards(ArrayList<Card> cards){
        this.cards = new ArrayList<>(cards);
    }

    // Removes a card from the pack and returns it.
    // For distributing cards.
    @Override
    public synchronized Card drawCard(){
        if(cards.isEmpty()){
            throw new IllegalArgumentException("The pack is empty!");
        }
        //size--;
        return cards.remove(cards.size()-1);
    }

    // Adds a card to the pack
    @Override
    public synchronized void addCard(Card card){
        cards.add(cards.size(), card);
        //size++;
    }

}
