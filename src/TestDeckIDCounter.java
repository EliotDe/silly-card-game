import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDeckIDCounter {

    DeckIDCounter counter;

    @Before
    public void setup(){
        counter = new DeckIDCounter();
    }

    @Test
    public void testNextId(){
        int id1 = counter.nextId();
        int id2 = counter.nextId();
        int id3 = counter.nextId();
        int id4 = counter.nextId();
        assertEquals(4, id4);
    }

}
