package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicLong;

public class Pack implements PackInterface {

    /*
     *
     *
     *
     */

    protected ArrayList<Card> cards = new ArrayList<>();
    protected Integer size = cards.size();

    public Pack(){}

    @Override
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
                    addCard(new Card(new AtomicLong(Long.parseLong(data))));
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

    @Override
    public synchronized int getPackSize(){
        /*
         *
         *
         *
         */
        return this.size;
    }

    @Override
    public synchronized ArrayList<Card> getCards(){
        /*
         *
         *
         *
         */
        return cards;
    }

    @Override
    public synchronized void setCards(ArrayList<Card> cards){
        /*
         *
         *
         *
         */
        this.cards = cards;
    }

    @Override
    public synchronized Card drawCard(){
        /*
         *
         *
         *
         */
        if(cards.isEmpty()){
            throw new IllegalArgumentException("The pack is empty!");
        }
        size--;
        return cards.remove(cards.size()-1);
    }

    @Override
    public synchronized void addCard(Card card){
        /*
         *
         *
         *
         */
        cards.add(cards.size(), card);
    }

}
