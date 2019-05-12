package creatures;


import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;

    public Clorus() {
        this(1);
    }

    public Clorus(double e) {
        super("clorus");
        energy = e;
        r = 0;
        g = 0;
        b = 0;
    }

    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return new Color(r, g, b);
    }

    @Override
    public void attack(Creature c) {
        // do nothing.
        energy += c.energy();
    }

    @Override
    public void move() {
        // TODO
        energy -= 0.03;
        if (energy < 0) {
            energy = 0;
        }
    }

    @Override
    public void stay() {
        // TODO
        energy -= 0.01;

    }
    @Override
    public Clorus replicate() {

        this.energy = this.energy / 2;
        Clorus rep = new Clorus(this.energy);
        return rep;
    }
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        /* Direction is enum. */
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        for (Direction d: neighbors.keySet()) {
            if (neighbors.get(d).name().equals("plip")) {
                plipNeighbors.addFirst(d);

            } else if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.addFirst(d);
            }
        }

        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        if (plipNeighbors.size() != 0) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        }

        if (this.energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }

        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }
}
