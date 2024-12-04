package main.java;

public class CardGame {

    /*
    *
    *
    *
    */

    private Player[] playerArray;
    private Deck[] deckArray;

    public Cardgame(Player[] playerArray, Deck[] deckArray){
        this.playerArray = playerArray;
        this.deckArray = deckArray;
    }

    public Player[] getPlayerArray() {
        return playerArray;
    }

    public void setPlayerArray(Player[] playerArray){
        this.playerArray = playerArray;
    }

    public Deck[] getDeckArray() {
        return deckArray;
    }

    public void setDeckArray(Deck[] deckArray){
        this.deckArray = deckArray;
    }

    public void gameLoop(){

    }

    public void playerTurn(){
        //if decks are empty, make player wait?

    }

    public Boolean checkVictoryReached(){

        //has 4 of the same cards

        return null;
    }

    public void checkIllegalMove(){

    }

    public void announceWinner() {

    }

    //Player array
    //deck array
    //game loop
    //checkvictoryreACHED
    //checkillegalmove?


}
