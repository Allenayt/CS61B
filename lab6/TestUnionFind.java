import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void testUnionFind() {
        UnionFind lst = new UnionFind(5);
        assertEquals(lst.find(4), 4);
        lst.union(2, 4);
        lst.union(1, 3);
        assertEquals(lst.find(2), 4);
        assertTrue(lst.connected(2, 4));
        assertFalse(lst.connected(1, 0));
        assertEquals(lst.sizeOf(1), 2);
        /*********************/
        lst.union(1, 0);
        assertEquals(lst.sizeOf(1), 3);
        assertFalse(lst.connected(2, 0));
        /*********************/
        lst.union(2, 0);
        assertEquals(lst.sizeOf(4), 5);
        assertTrue(lst.connected(4, 1));
        try {
            lst.union(6, 8);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Invalid input!");
        }
    }

}
