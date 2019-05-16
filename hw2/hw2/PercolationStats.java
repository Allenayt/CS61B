package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {


    private double meanValue;
    private double stddevValue;
    private double confidenceLowValue;
    private double confidenceHighValue;
    private int length;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be greater than zero!");
        }


        length = N;
        double[] portion = new double[T];
        for (int i = 0; i < T; i++) {
            portion[i] = experiment(pf);
        }

        meanValue = StdStats.mean(portion);
        stddevValue = StdStats.stddev(portion);
        confidenceLowValue = meanValue - (1.96 * stddevValue / (Math.sqrt(T)));
        confidenceHighValue = meanValue + (1.96 * stddevValue / (Math.sqrt(T)));

    }

    public double mean() {
        return meanValue;
    }

    public double stddev() {
        return stddevValue;
    }

    public double confidenceLow() {
        return confidenceLowValue;
    }

    public double confidenceHigh() {
        return confidenceHighValue;
    }

    private double experiment(PercolationFactory pf) {
        Percolation per = pf.make(length);
        while (!per.percolates()) {
            randomOpen(per);
        }
        return (double) per.numberOfOpenSites() / (double) (length * length);
    }

    private void randomOpen(Percolation p) {
        int x = StdRandom.uniform(length);
        int y = StdRandom.uniform(length);
        p.open(x, y);
    }
}
