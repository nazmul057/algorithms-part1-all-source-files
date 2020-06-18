// import edu.princeton.cs.algs4.StdOut;
// import edu.princeton.cs.algs4.StdRandom;
// import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// New code

public class Percolation {
    private final int totalRows;
    private final int num;
    private boolean[] statesOfSites;
    private final WeightedQuickUnionUF sites;
    private int numberOfOpenSitesCount = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.totalRows = n;
        this.num = (n*n) + 2;
        this.statesOfSites = new boolean[this.num]; // all initialized to false
        this.sites = new WeightedQuickUnionUF(this.num);

        // for(int i = 1; i < n+1; i++){
        //  sites.union(0, i);
        // sites.union(N-1, N-1-i);
        // }
        // StdOut.println("Percolation Initialized");
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > this.totalRows || col > this.totalRows)
            throw new IllegalArgumentException("rows and columns must be larger than 1");
        else if (!this.statesOfSites[((row - 1)*this.totalRows) + col]) {
            int element = ((row - 1)*this.totalRows) + col;
            int connectElement = 0;
            this.statesOfSites[element] = true;
            this.numberOfOpenSitesCount += 1;

            // Handeling the Rows

            if (row == 1 && this.totalRows == 2) {
                this.sites.union(element, 0);
                if (this.isOpen(row+1, col)) {
                    connectElement = this.totalRows + col; // connectElement = (((row+1) - 1)*this.totalRows) + col;
                    this.sites.union(element, num - 1);
                    this.sites.union(connectElement, num -1);
                }
            }
            else if (row == 1) {
                this.sites.union(element, 0);
                if (this.isOpen(row+1, col)) {
                    connectElement = this.totalRows + col; // connectElement = (((row+1) - 1)*this.totalRows) + col;
                    this.sites.union(element, connectElement);
                }
            }
            else if (row == this.totalRows) {
                // this.sites.union(element, this.num-1);
                if (this.isOpen(row-1, col)) {
                    connectElement = ((this.totalRows - 2) * this.totalRows) + col;
                    this.sites.union(element, num-1);
                    this.sites.union(connectElement, num-1);
                }
            }
            else {
                if (this.isOpen(row+1, col)) {
                    if (row == (this.totalRows-1)) {
                        connectElement = (((row + 1) - 1) * this.totalRows) + col;
                        this.sites.union(element, num -1);
                        this.sites.union(connectElement, num -1);
                    }
                    else {
                        connectElement = (((row + 1) - 1) * this.totalRows) + col;
                        this.sites.union(element, connectElement);
                    }
                }
                if (this.isOpen(row-1, col)) {
                    connectElement = (((row-1) - 1)*this.totalRows) + col;
                    this.sites.union(element, connectElement);
                }
            }

            // Handeling the columns

            if (col == 1) {
                if (this.isOpen(row, col+1)) {
                    connectElement = ((row - 1)*this.totalRows) + (col+1); // connectElement = (((row+1) - 1)*this.totalRows) + col;
                    this.sites.union(element, connectElement);
                }
            }
            else if (col == this.totalRows) {
                if (this.isOpen(row, col-1)) {
                    connectElement = ((row - 1)*this.totalRows) + (col-1);
                    this.sites.union(element, connectElement);
                }
            }
            else {
                if (this.isOpen(row, col+1)) {
                    connectElement = ((row - 1)*this.totalRows) + (col+1);
                    this.sites.union(element, connectElement);
                }
                if (this.isOpen(row, col-1)) {
                    connectElement = ((row - 1)*this.totalRows) + (col-1);
                    this.sites.union(element, connectElement);
                }
            }
            // End of handeling of rows and columns
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > this.totalRows || col > this.totalRows)
            throw new IllegalArgumentException("rows and columns must be larger than 1");
        else {
            int i = ((row - 1)*this.totalRows) +col;
            // boolean state = this.statesOfSites[i];
            return this.statesOfSites[i];
        }

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > this.totalRows || col > this.totalRows)
            throw new IllegalArgumentException("rows and columns must be larger than 1");
        else {
            int i = ((row - 1)*this.totalRows) +col;
            // boolean fullOrNot = false;
            if (this.sites.find(i) == this.sites.find(0)) return true;
            else return false;
            // return fullOrNot;
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numberOfOpenSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        //boolean percolatesOrNot = false;

        if (this.sites.find(0) == this.sites.find(this.num-1)) return true;
        else return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        /*
        int numberOfRowsOrColumns = 4;
        StdOut.println("Starting the test");
        Percolation modelPercolation = new Percolation(numberOfRowsOrColumns);
        while(true){//while(modelPercolation.percolates() == false){
            int p = StdRandom.uniform(1, numberOfRowsOrColumns+1);
            int q = StdRandom.uniform(1, numberOfRowsOrColumns+1);
            StdOut.println(p+" "+q);

            if(modelPercolation.isOpen(p,q) == false){
                modelPercolation.open(p,q);
            }
            if(modelPercolation.percolates() == true){
                int totalOpenElements = modelPercolation.numberOfOpenSites();
                double score = (double) totalOpenElements/(numberOfRowsOrColumns * numberOfRowsOrColumns);
                StdOut.println("The score is: " + score);
                break;
            }
        }
        StdOut.println("End of Test");


        for(int v=1; v<=4; v++){
            StdOut.println(modelPercolation.isFull(4, v));
        }

         */


    }



}


