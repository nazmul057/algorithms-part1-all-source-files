import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;



public class PercolationStats {
    // private int numberOfRowsOrColumns = 0;
    private final int numberOfTrials;
    private final double[] allStats;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        //this.numberOfRowsOrColumns = n;
        this.numberOfTrials = trials;
        this.allStats = new double[trials];

        // int numberOfRowsOrColumns = 100;
        // StdOut.println("Starting the test");
        for (int eachTrial = 0; eachTrial < trials; eachTrial++) {
            Percolation modelPercolation = new Percolation(n);
            while (true) {
                int p = StdRandom.uniform(1, n + 1);
                int q = StdRandom.uniform(1, n + 1);
                // StdOut.println(p + " " + q);

                if (modelPercolation.isOpen(p, q) == false) {
                    modelPercolation.open(p, q);
                }
                if (modelPercolation.percolates() == true) {
                    int totalOpenElements = modelPercolation.numberOfOpenSites();
                    this.allStats[eachTrial] = (double) totalOpenElements / (n * n);
                    // StdOut.println("The score is: " + score);

                    // this.allStats[eachTrial] = score;

                    break;
                }
            }
            // StdOut.println("Score in this trial: " + this.allStats[eachTrial]);
            // StdOut.println("End of Test");
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        double theMean = StdStats.mean(this.allStats);
        return theMean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double theStddev = StdStats.stddev(this.allStats);
        return theStddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double x = StdStats.mean(this.allStats);
        double s = StdStats.stddev(this.allStats);
        double T = this.numberOfTrials; // Auto type casting
        double theConfidenceLo = x - ((1.96*s)/Math.sqrt(T));
        return theConfidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double x = StdStats.mean(this.allStats);
        double s = StdStats.stddev(this.allStats);
        double T = this.numberOfTrials; // Auto type casting
        double theConfidenceHi = x + ((1.96*s)/Math.sqrt(T));
        return theConfidenceHi;

    }

    // test client (see below)
    public static void main(String[] args) {
        StdOut.print("Please Enter n(to create a nxn model) and number of trials separated by a Space: ");
        int modelDimension = StdIn.readInt();
        int modelTrials = StdIn.readInt();
        // StdOut.println(modelDimension+ " " + modelTrials);
        PercolationStats testModel = new PercolationStats(modelDimension, modelTrials);

        StdOut.println("Mean = " + testModel.mean());
        StdOut.println("Stddev = " + testModel.stddev());
        StdOut.println("95% of confidence interval = [" + testModel.confidenceLo()+", "+ testModel.confidenceHi()+"]");

    }


}



