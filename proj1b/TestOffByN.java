import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {


    // Your tests go here.
    @Test
    public void testOffByN(){

        OffByN offBy3 = new OffByN(3);
        OffByN offBy5 = new OffByN(5);
        assertTrue(offBy3.equalChars('a', 'd'));
        assertTrue(offBy3.equalChars('z', 'w'));
        assertFalse(offBy3.equalChars('c', 'd'));
        assertTrue(offBy5.equalChars('A', 'F'));
        assertFalse(offBy5.equalChars('E', 'F'));

    }
}
