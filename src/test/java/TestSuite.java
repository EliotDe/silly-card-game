package test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestCard.class,
        TestPack.class,
        TestDeck.class,
        TestHand.class,
        TestMain.class,
        TestCardGame.class,
        TestPlayer.class,
        TestIDCounter.class,
        TestDeckIDCounter.class,
        TestGameLogger.class})

public class TestSuite {
}
