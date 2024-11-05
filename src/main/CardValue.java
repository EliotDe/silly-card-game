package main;

public enum CardValue {
    ACE ( 1),
    TWO ( 2),
    THREE ( 3),
    FOUR ( 4),
    FIVE ( 5),
    SIX ( 6),
    SEVEN ( 7),
    EIGHT ( 8),
    NINE ( 9),
    JACK ( 10),
    QUEEN ( 11),
    KING ( 12);

    public int cardIntValue;

    private CardValue(int cardIntValue){
        this.cardIntValue = cardIntValue;
    }

    public int getCardIntValue(){
        return cardIntValue;
    }
}
