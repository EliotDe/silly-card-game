import java.util.Scanner;

public class CardGame {

        /*
         * Main class to run the game, compile this and run to play
         */

    public static void main(String[] args) {
        System.out.println("----------Welcome to the silly card game------------\n\n\n");
        GameLoop cg = new GameLoop();
        cg.mainLoop(new Scanner(System.in));
        System.out.println("hope you enjoyed :)");
    }
}