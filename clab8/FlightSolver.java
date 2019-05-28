import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    PriorityQueue<Flight> startMinPQ;
    PriorityQueue<Flight> endMinPQ;

    public FlightSolver(ArrayList<Flight> flights) {

        Comparator<Flight> startComp = (f1, f2) -> f1.startTime - f2.startTime;
        Comparator<Flight> endComp = (f1, f2) -> f1.endTime - f2.endTime;
        startMinPQ = new PriorityQueue<>(startComp);
        endMinPQ = new PriorityQueue<>(endComp);
        for (Flight f: flights) {
            startMinPQ.add(f);
            endMinPQ.add(f);
        }
    }

    /* 构造函数的目的是将所有flight录入, 并且都加进MinPQ */

    public int solve() {
        int temp = 0;
        int timeMax = 0;
        while (!startMinPQ.isEmpty()) {
            Flight startFl = startMinPQ.peek();
            Flight endFl = endMinPQ.peek();
            if (startFl.startTime <= endFl.endTime) {
                temp += startMinPQ.remove().passengers;
                if (temp > timeMax) {
                    timeMax = temp;
                }
            } else {
                temp -= endMinPQ.remove().passengers;
            }
        }
        return timeMax;
    }

}
