package main.java;

import java.util.ArrayList;

public interface PackInterface {
    void initialisePack(int cardNumber);
    int getPackSize();
    ArrayList<Card> getCards();
    void setCards(ArrayList<Card> cards);
    Card drawCard();
    void addCard(Card card);
}

