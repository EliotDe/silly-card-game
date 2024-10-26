public class Player implements PlayerInterface{
    private Hand hand = new Hand();
    private String username;

    public Player(String username){
        this.username = username;
    }

    public void setHand(Hand hand){this.hand = hand;}
    public Hand getHand(){return hand;}

    public String getUsername(){return username;}


}
