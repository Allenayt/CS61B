package creatures;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;

import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {

    /******** Codes from https://github.com/dingqy/Berkley-CS61B/blob/master/lab5/creatures/Clorus.java *******/
    @Test
    public void testName() {
        Clorus cl = new Clorus();
        assertEquals("clorus", cl.name());
        assertNotEquals("clors", cl.name());
    }

    @Test
    public void testMove() {
        Clorus cl = new Clorus(3);
        cl.move();
        assertEquals(2.97, cl.energy(), 0.001);
        Clorus c2 = new Clorus(0.01);
        c2.move();
        assertEquals(0.0, c2.energy(), 0.0001);
    }

    @Test
    public void testStay() {
        Clorus cl = new Clorus(0.8);
        cl.stay();
        assertEquals(0.79, cl.energy(), 0.01);
    }

    @Test
    public void testAttack() {
        Clorus cl = new Clorus(1.3);
        Plip p = new Plip(1.1);
        cl.attack(p);
        assertEquals(2.4, cl.energy(), 0.001);
    }

    @Test
    public void testReplicate() {
        Clorus cl = new Clorus(3);
        Clorus cl2 = cl.replicate();
        assertNotEquals(cl, cl2);
        assertEquals(1.5, cl2.energy(), 0.01);
        assertEquals(1.5, cl.energy(), 0.01);
        Clorus cl3 = new Clorus(0);
        Clorus cl4 = cl3.replicate();
        assertNotEquals(cl3, cl4);
        assertEquals(0, cl3.energy(), 0.01);
        assertEquals(0, cl4.energy(), 0.01);
    }

    @Test
    public void testChooseAction() {
        //Test the Rule1
        Clorus cl = new Clorus(3.0);
        Plip p = new Plip(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, p);

        Action actual = cl.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Test the Rule 2 (Attack)
        cl = new Clorus(3.0);
        p = new Plip(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, p);
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = cl.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space. (Rule 3)
        cl = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty2 = new HashMap<Direction, Occupant>();
        topEmpty2.put(Direction.TOP, new Empty());
        topEmpty2.put(Direction.BOTTOM, new Impassible());
        topEmpty2.put(Direction.LEFT, new Impassible());
        topEmpty2.put(Direction.RIGHT, new Impassible());

        actual = cl.chooseAction(topEmpty2);
        Action unexpected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(unexpected, actual);

        // Energy < 1; stay.
        cl = new Clorus(.99);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Impassible());
        allEmpty.put(Direction.LEFT, new Impassible());
        allEmpty.put(Direction.RIGHT, new Impassible());

        actual = cl.chooseAction(allEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);
    }
}