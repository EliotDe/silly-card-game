package test;

import main.CardValue;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;

public class TestCardValue {
    @Test
    public void testGetCardValue(){
        CardValue[] values = {
        CardValue.ACE, CardValue.TWO,
        CardValue.THREE, CardValue.FOUR,
        CardValue.FIVE, CardValue.SIX,
        CardValue.SEVEN, CardValue.EIGHT,
        CardValue.NINE, CardValue.JACK,
        CardValue.QUEEN, CardValue.KING
        };

        //BICEP
        for(int i =0; i<12;i++){
            assertEquals(i+1, values[i].getCardIntValue());
        }
    }
}
