import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestCard.class,
        TestPack.class,
        TestDeck.class,
        TestHand.class,
//      TestCardGame.class, // Nothing to test in this class, only runs other classes
        TestGameLoop.class,
        TestPlayer.class,
        TestIDCounter.class,
        TestDeckIDCounter.class,
        TestDeckLogger.class,
        TestGameLogger.class
})


public class TestSuite {
}
