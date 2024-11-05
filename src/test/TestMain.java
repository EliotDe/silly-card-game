package test;
import org.junit.Test;

import java.util.InputMismatchException;

public class TestMain {

    @Test
    public void testBeginGame(){
        try{

        }catch(InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }
}
