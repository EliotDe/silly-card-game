import org.junit.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
//import org.junit.AfterAll;
//import org.junit.BeforeAll;

import static org.junit.Assert.*;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class TestPlayer {
    Hand hand1;
    Hand hand2;
    Hand hand3;
    Hand hand4;
    Hand winningHand;
    Deck deck1;
    Deck deck2;
    Deck deck3;
    Deck deck4;
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    ArrayList<Card> cards1;
    ArrayList<Card> cards2;
    ArrayList<Card> cards3;
    ArrayList<Card> cards4;
    ArrayList<Card> cards5;
    ArrayList<Card> cards6;
    ArrayList<Card> cards7;
    ArrayList<Card> cards8;
    ArrayList<Card> winningCards;

    AtomicInteger x = new AtomicInteger(1);

//    @BeforeAll
//    public static void setUpBeforeClass(){
//        //empty
//    }

    @Before
    public void setup(){
        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        player4 = new Player();

        hand1 = new Hand();
        hand2 = new Hand();
        hand3 = new Hand();
        hand4 = new Hand();
        winningHand = new Hand();

        deck1 = new Deck();
        deck2 = new Deck();
        deck3 = new Deck();
        deck4 = new Deck();

        cards1 = new ArrayList<>();
        cards2 = new ArrayList<>();
        cards3 = new ArrayList<>();
        cards4 = new ArrayList<>();
        cards5 = new ArrayList<>();
        cards6 = new ArrayList<>();
        cards7 = new ArrayList<>();
        cards8 = new ArrayList<>();
        winningCards = new ArrayList<>();

        cards1.add(new Card(new AtomicInteger(1)));
        cards1.add(new Card(new AtomicInteger(2)));
        cards1.add(new Card(new AtomicInteger(3)));
        cards1.add(new Card(new AtomicInteger(2)));
        hand1.setCards(cards1);
        cards2.add(new Card(new AtomicInteger(2)));
        cards2.add(new Card(new AtomicInteger(6)));
        cards2.add(new Card(new AtomicInteger(7)));
        cards2.add(new Card(new AtomicInteger(8)));
        hand2.setCards(cards2);
        cards3.add(new Card(new AtomicInteger(9)));
        cards3.add(new Card(new AtomicInteger(2)));
        cards3.add(new Card(new AtomicInteger(10)));
        cards3.add(new Card(new AtomicInteger(41)));
        hand3.setCards(cards3);
        cards4.add(new Card(new AtomicInteger(100)));
        cards4.add(new Card(new AtomicInteger(2)));
        cards4.add(new Card(new AtomicInteger(30)));
        cards4.add(new Card(new AtomicInteger(2)));
        hand4.setCards(cards4);
        winningCards.add(new Card(new AtomicInteger(1)));
        winningCards.add(new Card(new AtomicInteger(1)));
        winningCards.add(new Card(new AtomicInteger(1)));
        winningCards.add(new Card(new AtomicInteger(1)));
        winningHand.setCards(winningCards);
        cards5.add(new Card(new AtomicInteger(2)));
        cards5.add(new Card(new AtomicInteger(21)));
        cards5.add(new Card(new AtomicInteger(31)));
        cards5.add(new Card(new AtomicInteger(9)));
        deck1.setCards(cards5);
        cards6.add(new Card(new AtomicInteger(10)));
        cards6.add(new Card(new AtomicInteger(3)));
        cards6.add(new Card(new AtomicInteger(15)));
        cards6.add(new Card(new AtomicInteger(16)));
        deck2.setCards(cards6);
        cards7.add(new Card(new AtomicInteger(17)));
        cards7.add(new Card(new AtomicInteger(2)));
        cards7.add(new Card(new AtomicInteger(18)));
        cards7.add(new Card(new AtomicInteger(41)));
        deck3.setCards(cards7);
        cards8.add(new Card(new AtomicInteger(19)));
        cards8.add(new Card(new AtomicInteger(20)));
        cards8.add(new Card(new AtomicInteger(21)));
        cards8.add(new Card(new AtomicInteger(22)));
        deck4.setCards(cards8);
    }

    @Test
    public void testRun(){
        player1.setHand(hand1);
        player1.setLeftDeck(deck4);
        player1.setRightDeck(deck1);
        player1.addDeckModificationListener(new DeckModificationListener(){
            @Override
            public synchronized void onDeckModified(DeckModificationEvent event){
                if (event.getAction().equals("Discard")){
                    player2.setLeftDeck(event.getDeck());
                }
            }
        });
        player1.addDeckModificationListener(new DeckModificationListener(){
            @Override
            public synchronized void onDeckModified(DeckModificationEvent event){
                if (event.getAction().equals("Draw")){
                    player4.setRightDeck(event.getDeck());
                }
            }
        });

        player2.setHand(hand2);
        player2.setLeftDeck(deck1);
        player2.setRightDeck(deck2);
        player2.addDeckModificationListener(new DeckModificationListener(){
            @Override
            public synchronized void onDeckModified(DeckModificationEvent event){
                if (event.getAction().equals("Discard")){
                    player3.setLeftDeck(event.getDeck());
                }
            }
        });
        player2.addDeckModificationListener(new DeckModificationListener(){
            @Override
            public synchronized void onDeckModified(DeckModificationEvent event){
                if (event.getAction().equals("Draw")){
                    player1.setRightDeck(event.getDeck());
                }
            }
        });

        player3.setHand(hand3);
        player3.setLeftDeck(deck2);
        player3.setRightDeck(deck3);
        player3.addDeckModificationListener(new DeckModificationListener(){
            @Override
            public synchronized void onDeckModified(DeckModificationEvent event){
                if (event.getAction().equals("Discard")){
                    player4.setLeftDeck(event.getDeck());
                }
            }
        });
        player3.addDeckModificationListener(new DeckModificationListener(){
            @Override
            public synchronized void onDeckModified(DeckModificationEvent event){
                if (event.getAction().equals("Draw")){
                    player2.setRightDeck(event.getDeck());
                }
            }
        });

        player4.setHand(hand4);
        player4.setLeftDeck(deck3);
        player4.setRightDeck(deck4);
        player4.addDeckModificationListener(new DeckModificationListener(){
            @Override
            public synchronized void onDeckModified(DeckModificationEvent event){
                if (event.getAction().equals("Discard")){
                    player1.setLeftDeck(event.getDeck());
                }
            }
        });
        player4.addDeckModificationListener(new DeckModificationListener(){
            @Override
            public synchronized void onDeckModified(DeckModificationEvent event){
                if (event.getAction().equals("Draw")){
                    player3.setRightDeck(event.getDeck());
                }
            }
        });

        ArrayList<Thread> threads = new ArrayList<>();
        Thread thread1 = new Thread(player1);
        Thread thread2 = new Thread(player2);
        Thread thread3 = new Thread(player3);
        Thread thread4 = new Thread(player4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        threads.add(thread1);
        threads.add(thread2);
        threads.add(thread3);
        threads.add(thread4);

        boolean gameRunning = true;
        while (gameRunning) {
            for (int i = 0; i < threads.size(); i++) {
                // Check if the thread is no longer alive or has been interrupted
                if (!threads.get(i).isAlive() || threads.get(i).isInterrupted()) {
                    gameRunning = false; // Stop the game loop
                    for (int j = 0; j < threads.size(); j++) {
                        // Interrupt all other running threads
                        if (threads.get(j).isAlive()) {
                            threads.get(j).interrupt();
                        }
                    }
                    break; // Exit the outer loop
                }
            }

            try {
                Thread.sleep(1000); // Small sleep to reduce CPU usage
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Ensure the main thread is not in an interrupted state
            }
        }

        assertNotNull(Player.winner.get());
        assertTrue(Player.winner.get().equals(player1.getUsername())||
                Player.winner.get().equals(player2.getUsername())||
                Player.winner.get().equals(player3.getUsername())||
                Player.winner.get().equals(player4.getUsername()));


        try{
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }


    }

    @Test
    public void testAddDeckModificationListener(){
        assertEquals(0, player1.getListeners().size());

        player1.addDeckModificationListener(new DeckModificationListener(){
            @Override
            public synchronized void onDeckModified(DeckModificationEvent event){
                if (event.getAction().equals("Discard")){
                    player2.setLeftDeck(event.getDeck());
                }
            }
        });

        assertEquals(1, player1.getListeners().size());
    }
    @Test
    public void testFireDeckModificationEvent(){
        player4.setRightDeck(deck4);
        player1.setLeftDeck(deck4);
        player1.setRightDeck(deck1);
        player2.setLeftDeck(deck1);

        player1.addDeckModificationListener(new DeckModificationListener(){
            @Override
            public synchronized void onDeckModified(DeckModificationEvent event){
                if (event.getAction().equals("Discard")){
                    player2.setLeftDeck(event.getDeck());
                }
            }
        });

        player1.addDeckModificationListener(new DeckModificationListener(){
            @Override
            public synchronized void onDeckModified(DeckModificationEvent event){
                if (event.getAction().equals("Draw")){
                    player4.setRightDeck(event.getDeck());
                }
            }
        });

        //player1 has changed deck1 (shared with player2) to be deck3
        //test player2's left deck (deck1) becomes deck3
        player1.fireDeckModificationEvent(deck3, "Discard");
        assertEquals(deck3, player2.getLeftDeck());

        //player1 has changed deck4 (shared with player4) to be deck2
        //test player4's right deck (deck4) becomes deck2
        player1.fireDeckModificationEvent(deck2, "Draw");
        assertEquals(deck2, player4.getRightDeck());

    }
    @Test
    public void testDrawCardFromLeftDeck(){
        player1.setLeftDeck(deck4);
        //test initial size
        assertEquals(4, deck4.getPackSize());

        //test that card is drawn properly
        Card drawnCard = player1.drawCardFromLeftDeck();
        assertEquals(3, deck4.getPackSize());
        int size = cards8.size();
        assertEquals(cards8.get(size-1), drawnCard);
    }

    @Test
    public void testDiscardCardToRightDeck(){
        player1.setHand(hand1);
        player1.setRightDeck(deck1);

        assertEquals(4, hand1.getCards().size());
        Card cardToRemove = cards1.get(0);
        player1.discardCardToRightDeck(cardToRemove);
        assertEquals(3, player1.getHand().getCards().size());
        assertFalse(player1.getHand().getCards().contains(cardToRemove));
    }

    @Test
    public void testPickCard(){

        // Test skips preferred denominations
        Hand newHand = createHand(1, 2, 3, 4);
        player1.setHand(newHand);
        Card pickedCard = player1.pickCard();
        assertEquals(newHand.getCards().get(1), pickedCard);

        // Test all singles pick first
        Hand newHand5 = createHand(2, 3, 4, 5, 6);
        player1.setHand(newHand5);
        Card pickedCard5 = player1.pickCard();
        assertEquals(newHand5.getCards().get(0), pickedCard5);
    }

    private Hand createHand(int... values) {
        ArrayList<Card> cards = new ArrayList<>();
        for (int value : values) {
            cards.add(new Card(new AtomicInteger(value)));
        }
        Hand hand = new Hand();
        hand.setCards(cards);
        return hand;
    }

    @Test
    public void testCheckVictory(){

        player1.setHand(winningHand);

        assertEquals(true, player1.checkVictory());
    }

    @After
    public void tearDown(){
        hand1 = null;
        hand2 = null;
        hand3 = null;
        hand4 = null;
        winningHand = null;
        deck1 = null;
        deck2 = null;
        deck3 = null;
        deck4 = null;
        player1 = null;
        player2 = null;
        player3 = null;
        player4 = null;
        cards1 = null;
        cards2 = null;
        cards3 = null;
        cards4 = null;
        cards5 = null;
        cards6 = null;
        cards7 = null;
        cards8 = null;
        IDCounter.resetCounter();
        DeckIDCounter.resetCounter();
    }

//    @AfterAll
//    public static void tearDownAll(){
//        //empty
//    }
}
