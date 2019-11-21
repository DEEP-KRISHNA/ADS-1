import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private double percoconlo;
    private double percoconhi;
    private double percomean;
    private double percostddev;
    private double[] percosta;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int m, int trials) {
        int n = m;
        int t = trials;
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException();
        }
        percosta = new double[t];
        for (int i = 0; i < t; i++) {
            Percolation perco = new Percolation(n);
            while (perco.percolates() == false) {
                int c = StdRandom.uniform(1, n + 1);
                int r = StdRandom.uniform(1, n + 1);
                if (perco.isOpen(r, c) == false) {
                    perco.open(r, c);
                }
            }
            int opsites = perco.numberOfOpenSites();
            double temp = (double) opsites / (n * n);
            percosta[i] = temp;
        }

        percomean = StdStats.mean(percosta);
        percostddev = StdStats.stddev(percosta);
        percoconhi = percomean + (1.96 * percostddev / Math.sqrt(t));
        percoconlo = percomean - (1.96 * percostddev / Math.sqrt(t));
    }

    // sample mean of percolation threshold
    public double mean() {
        return percomean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return percostddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return percoconlo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return percoconhi;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
    }

}