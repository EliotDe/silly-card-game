import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface PackInterface {
    void initialisePack(int cardNumber);
    String getFilePathFromUser();
    ArrayList<String> readFile(String filePath) throws FileNotFoundException;
    int getPackSize();
    ArrayList<Card> getCards();
    void setCards(ArrayList<Card> cards);
    Card drawCard();
    void addCard(Card card);
}

