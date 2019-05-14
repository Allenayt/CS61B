package hw2;

import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {


    private double meanValue;
    private double stddevValue;
    private double confidenceLowValue;
    private double confidenceHighValue;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be greater than zero!");
        }

        // Perform a series of random experiments.
        double[] res = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation per = pf.make(N);
            while(!per.percolates()) {
                int row = (int)(Math.random() * N);
                int col = (int)(Math.random() * N);
                per.open(row, col);
            }
            res[i] = (double) per.numberOfOpenSites() / (double)(N * N) ;

        }

        meanValue = StdStats.mean(res);
        stddevValue = StdStats.stddev(res);
        confidenceLowValue = meanValue - 1.96 * stddevValue / Math.sqrt(T);
        confidenceHighValue = meanValue + 1.96 * stddevValue / Math.sqrt(T);


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
}
