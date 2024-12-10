import org.junit.Before;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestIDCounter {

    IDCounter counter;

    @Before
    public void setup(){
        counter = new IDCounter();
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