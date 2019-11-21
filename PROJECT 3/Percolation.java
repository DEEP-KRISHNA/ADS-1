// import java.util.Scanner;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] perco;
    private WeightedQuickUnionUF weightperco;
    private int n;
    private int opensites = 0;
    private int firstVirtual;
    private int lastVirtual;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int m) {
        n = m;
        firstVirtual = n * n;
        lastVirtual = (n * n) + 1;
        if (m <= 0) {
            throw new IllegalArgumentException();
        }
        perco = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                perco[i][j] = false;
            }
        }
        weightperco = new WeightedQuickUnionUF((n * n) + 2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int roww, int coll) {
        int row = roww - 1;
        int col = coll - 1;
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IllegalArgumentException();
        }
        perco[row][col] = true;
        opensites++;
        // weightperco.union((row * n) + col, (row * n) + col);
        if (row - 1 >= 0) {
            if (perco[row - 1][col] == true) {
                weightperco.union((row * n) + col, ((row - 1) * n) + col);
            }
        }
        if (row + 1 < n) {
            if (perco[row + 1][col] == true) {
                weightperco.union((row * n) + col, ((row + 1) * n) + col);
            }
        }
        if (col - 1 >= 0) {
            if (perco[row][col - 1] == true) {
                weightperco.union((row * n) + col, (row * n) + col - 1);
            }
        }
        if (col + 1 < n) {
            if (perco[row][col + 1] == true) {
                weightperco.union((row * n) + col, (row * n) + col + 1);
            }
        }
        if (row == 0) {
            weightperco.union(col, firstVirtual);
        }
        if (row == n - 1) {
            weightperco.union((row * n) + col, lastVirtual);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int roww, int coll) {
        int row = roww - 1;
        int col = coll - 1;
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IllegalArgumentException();
        }
        return perco[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int roww, int coll) {
        int row = roww - 1;
        int col = coll - 1;
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IllegalArgumentException();
        }
        // boolean flag = false;
        // for (int j = 0; j < n; j++) {
        // if (weightperco.connected(j, (row * n) + col)) {
        // flag = true;
        // }
        // }

        return (perco[row][col] && (weightperco.connected(firstVirtual, (row * n) + col)));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return opensites;
    }

    // does the system percolate?
    public boolean percolates() {
        // for (int j = 0; j < n; j++) {
        // for (int k = 0; k < n; k++) {
        // if (weightperco.connected(j, k + ((n - 1) * n))) {
        // return true;
        // }
        // }
        // }
        return weightperco.connected(firstVirtual, lastVirtual);
        // return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        // Scanner scan = new Scanner(System.in);
        // int n = scan.nextInt();
        // Percolation per = new Percolation(n);
        // while (scan.hasNext()) {
        // per.open(scan.nextInt() - 1, scan.nextInt() - 1);
        // }
        // System.out.println(per.percolates());
    }
}