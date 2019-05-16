package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int len;
    private boolean[][] isCellOpen;
    private int openNumber;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF lstFormal;
    private WeightedQuickUnionUF lstHelper;

    public Percolation(int N) {

        lenValidate(N);

        len = N;
        isCellOpen = new boolean[N][N];
        openNumber = 0;
        top = N * N;
        bottom = N * N + 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                isCellOpen[i][j] = false;
            }
        }
        lstFormal = new WeightedQuickUnionUF(N * N + 2);
        lstHelper = new WeightedQuickUnionUF(N * N + 1);

    }

    public void open(int row, int col) {
        if(!boundValidate(row, col)) {
            throw new IndexOutOfBoundsException();
        } else if (isCellOpen[row][col]) {
            return;
        }
        isCellOpen[row][col] = true;
        openNumber += 1;
        int num = xyTo1D(row, col);
        connect(num, row-1, col);
        connect(num, row+1, col);
        connect(num, row, col-1);
        connect(num, row, col+1);
        checkTopBottom(num);
    }

    public boolean isOpen(int row, int col) {
        boundValidate(row, col);
        return isCellOpen[row][col];
    }

    public boolean isFull(int row, int col) {
        //boundValidate(row, col);
        if (!boundValidate(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return isCellOpen[row][col] && lstHelper.connected(top, xyTo1D(row, col));
        //Why lstHelper is needed?
    }

    public int numberOfOpenSites() {
        return openNumber;
    }

    public boolean percolates() {
        return lstFormal.connected(top, bottom);
    }

    private int xyTo1D(int x, int y) {
        return x * len + y;
    }

    private void lenValidate(int N) {
        if (N <= 0) {
            throw new IndexOutOfBoundsException("N must be larger than 0.");
        }
    }

    private boolean boundValidate(int row, int col) {
        if (row >= 0 && row <= len - 1 && col >= 0 && col <= len - 1) {
            return true;
        }
        return false;

    }

    private void connect(int a, int row, int col) {
        if (boundValidate(row, col) && isCellOpen[row][col]) {
            lstFormal.union(a, xyTo1D(row, col));
            lstHelper.union(a, xyTo1D(row, col));
        }
    }

    private void checkTopBottom(int num) {
        if (0 <= num && num < len - 1) {
            lstFormal.union(num, top);
            lstHelper.union(num, top);
        }
        else if (num < len * len && num >= len * len - len) {
            lstFormal.union(num, bottom);
        }
    }

    public static void main(String[] args) {
        PercolationStats pStats = new PercolationStats(20, 1000, new PercolationFactory());
        System.out.println(pStats.mean());
        System.out.println(pStats.stddev());
        System.out.println(pStats.confidenceLow());
        System.out.println(pStats.confidenceHigh());
    }
}

