package test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestCard.class, //done
        TestPack.class, //done
        TestDeck.class, //done
        TestHand.class, //done
//        TestCardGame.class, //done
        TestGameLoop.class, //done
        TestPlayer.class, //hard
        TestIDCounter.class, //done
        TestDeckIDCounter.class, //done
        TestDeckLogger.class, //done
        TestGameLogger.class //done
})


public class TestSuite {
}
