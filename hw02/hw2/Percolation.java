package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


    private int rowNumber;
    private int colNumber;
    private WeightedQuickUnionUF lst1;
    private WeightedQuickUnionUF lst2;
    private boolean[][] iscellOpen;
    private int openNumber;
    private int top;
    private int bottom;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("The input N must be larger than 0 ");
        }

        rowNumber = N;
        colNumber = N;
        iscellOpen = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; i < N; i++) {
                iscellOpen[i][j] = false;
            }
        }
        openNumber = 0;
        lst1 = new WeightedQuickUnionUF(N * N + 2);
        lst2 = new WeightedQuickUnionUF(N * N + 1);
        top = N * N;
        bottom = N * N + 1;
    }

    public void open(int row, int col) {
        if(!cellValidate(row, col)){
            throw new IndexOutOfBoundsException();
        } else if (iscellOpen[row][col]) {
            return;
        }
        iscellOpen[row][col] = true;
        openNumber += 1;
        int cell = xyTo1D(row, col);
        cellUnion(row+1, col, cell);
        cellUnion(row-1, col, cell);
        cellUnion(row, col+1, cell);
        cellUnion(row+1, col-1, cell);
        checkTopBottom(row, col);
    }

    public boolean isOpen(int row, int col) {
        if(!cellValidate(row, col)){
            throw new IndexOutOfBoundsException();
        }
        return iscellOpen[row][col];
    }

    public boolean isFull(int row, int col) {
        if(!cellValidate(row, col)){
            throw new IndexOutOfBoundsException();
        }
        return isFullHelper(row, col);
    }

    public int numberOfOpenSites() {
        return openNumber;
    }

    public boolean percolates() {
        return lst1.connected(top, bottom);
    }

    private int xyTo1D(int x, int y) {
        return colNumber * x + y;
    }

    private boolean cellValidate(int row, int col) {
        return 0 <= row && row < rowNumber && 0 <= col && col < colNumber;
    }

    private void cellUnion(int row, int col, int cell) {
        if (cellValidate(row, col) && iscellOpen[row][col]) {
            lst1.union(xyTo1D(row, col), cell);
            lst2.union(xyTo1D(row, col), cell);
        }
    }

    private boolean isFullHelper(int row, int col) {
        int cellPos = xyTo1D(row, col);
        return lst2.connected(cellPos, top);
    }

    private void checkTopBottom  (int row, int col) {
        // If the cell is at top or bottom, this method will connect it with top or bottom.
        int cellPos = xyTo1D(row, col);
        if(row == 0) {
            lst1.union(cellPos, top);
            lst2.union(cellPos, top);
        } else if (row == rowNumber - 1) {
            lst1.union(cellPos, bottom);
        }
    }

    public static void main(String[] args) {
        PercolationStats pStats = new PercolationStats(100, 200000, new PercolationFactory());
        System.out.println(pStats.mean());
        System.out.println(pStats.stddev());
        System.out.println(pStats.confidenceLow());
        System.out.println(pStats.confidenceHigh());
    }
}
