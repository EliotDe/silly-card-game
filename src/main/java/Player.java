package main.java;

public class Player implements PlayerInterface {

    /*
     * make threadable
     *
     *
     */

    //setpreferredcard

    private Hand hand = new Hand();
    private final String username;
    private final Long preferredValue;

    public Player(){
        Long id = IDCounter.nextId();
        this.preferredValue = id;
        this.username = "Player" + id;
    }

    public synchronized void setHand(Hand hand){this.hand = hand;}
    public synchronized Hand getHand(){return hand;}

    public synchronized Long getPreferredValue(){return preferredValue;}

    public String getUsername(){return username;}


}
