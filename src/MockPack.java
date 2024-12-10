import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MockPack implements PackInterface {
    private ArrayList<Card> cards = new ArrayList<Card>();
    private int size = 0;

    @Override
    public void initialisePack(int cardNumbers) {
        cards.clear();
        for (int i = 0; i < cardNumbers; i++) {
            Card card = null;
            if(i<4){
                card = new Card(new AtomicInteger(1));
            }
            else{
                card = new Card(new AtomicInteger(i+1));
            }
            cards.add(card);
        }
        size = cards.size();
    }

    @Override
    public String getFilePathFromUser(){
        return "mockFilePath.txt";
    }

    @Override
    public ArrayList<String> readFile(String filePath) throws FileNotFoundException{
        return new ArrayList<>();
    }

    @Override
    public int getPackSize() {
        return size;
    }

    @Override
    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public void setCards(ArrayList<Card> cards) {
        this.cards = new ArrayList<>(cards);
        size = this.cards.size();
    }

    @Override
    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("The pack is empty!");
        }
        size--;
        return cards.remove(cards.size() - 1);
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
        size++;
    }
}

