package main.java;

public class Player implements PlayerInterface {

    /*
     * make threadable
     *
     *
     */

    //setpreferredcard

    private Hand hand = new Hand();
    private String username;

    public Player(){
        this.username = "Player" + IDCounter.nextId();
    }

    public void setHand(Hand hand){this.hand = hand;}
    public Hand getHand(){return hand;}

    public String getUsername(){return username;}


}
