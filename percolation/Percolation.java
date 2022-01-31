/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF wquf;

    public Percolation(int n) {
        wquf = new WeightedQuickUnionUF(n);


    }                // create n-by-n grid, with all sites blocked

    public void open(int row, int col) {

    }
    // open site (row, col) if it is not open already
    //    public boolean isOpen(int row, int col){} // is site (row, col) open?
    //    public boolean isFull(int row, int col){}  // is site (row, col) full?
    //    public     int numberOfOpenSites() {}      // number of open sites
    //    public boolean percolates() {}             // does the system percolate?


}
