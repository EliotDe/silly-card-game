package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Pack implements PackInterface {
    protected ArrayList<Card> cards = new ArrayList<>();
    protected Integer size = cards.size();

    public Pack(){}

    public void initialisePack(int cardNumber){
        Scanner scanner = new Scanner(System.in);
        boolean positive = cardNumber > 0;
        boolean multipleOfEight = cardNumber % 8 ==0;

        if (!(positive && multipleOfEight)){
            throw new IllegalArgumentException("Card number must be a positive multiple of eight");
        }

        try {
            int lineCount = 0;
            while (lineCount != cardNumber){
                System.out.println("Enter the location of the pack to load: ");
                File packFile = new File(scanner.nextLine());
                Scanner fileReader = new Scanner(packFile);

                lineCount = 0;
                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    addCard(new Card(Integer.parseInt(data)));
                    lineCount++;
                }

                if (lineCount != cardNumber){
                    System.out.println("Error, pack line count is not proportional to player number.");
                }

                fileReader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred: " + e);
        }

    }

    public synchronized int getPackSize(){
        return this.size;
    }

    public synchronized ArrayList<Card> getPack(){
        return cards;
    }

    public synchronized void setPack(ArrayList<Card> cards){
        this.cards = cards;
    }

    public synchronized Card drawCard(){
        return cards.remove(cards.size()-1);
    }

    public synchronized void addCard(Card card){
        cards.add(cards.size()-1, card);
    }

}
