import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class FastCollinearPoints {
    public LineSegment[] finalSegments;
    private int numberOfSegments = 0;
    private Point[] tempPoints;
    private ArrayList<LineSegment> tempLine = new ArrayList<LineSegment>();
    private ArrayList<PairPoint> pp = new ArrayList<PairPoint>();

    public FastCollinearPoints(Point[] points) {
        Point[] sortedPoints = new Point[points.length];
        Double[] sortedSlope = new Double[points.length];
        for (int i = 0; i < points.length; i++) {
            sortedPoints[i] = points[i];
        }
        for (int i = 0; i < points.length; i++) {
            Arrays.sort(sortedPoints, points[i].slopeOrder());
            int amountOfSame = 0;
            for (int j = 0; j < points.length - 1; j++) {
                if (sortedPoints[j].slopeTo(points[i]) == sortedPoints[j + 1].slopeTo(points[i])) {
                    amountOfSame++;
                    if (amountOfSame >= 2) {
                        tempPoints = new Point[amountOfSame + 2];
                        tempPoints[0] = sortedPoints[0];
                        for (int k = 1; k <= amountOfSame + 1; k++) {
                            tempPoints[k] = sortedPoints[j - amountOfSame + k];
                        }
                    }
                } else {
                    amountOfSame = 0;
                }
            }
            try {
                Arrays.sort(tempPoints);
            }catch (NullPointerException e){
                continue;
            }
            pp.add(new PairPoint(tempPoints[0],tempPoints[tempPoints.length-1]));
        }
        PairPoint[] ppa = pp.toArray(new PairPoint[0]);
        Arrays.sort(ppa);
        for(PairPoint p:ppa){
            tempLine.add(new LineSegment(p.x,p.y));
        }
    }    // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return segments().length;
    }       // the number of line segments

    public LineSegment[] segments() {
        for (int i = 0; i < tempLine.size(); i++) {
            if (i + 1 > tempLine.size()-1) {
                break;
            }

            if (tempLine.get(i).toString().equals(tempLine.get(i + 1).toString())) {
                tempLine.remove(i + 1);
                i=-1;
            } else {
                continue;
            }
        }

        finalSegments = tempLine.toArray(new LineSegment[0]);

        return finalSegments;
    }            // the line segments


    private class PairPoint implements Comparable<PairPoint>{
        public Point x;
        public Point y;
        public PairPoint(Point p, Point q) {
            if (p == null || q == null) {
                throw new NullPointerException("argument is null");
            }
            this.x = p;
            this.y = q;
        }
        public int compareTo(PairPoint that) {
            /* YOUR CODE HERE */
            if (that.x.compareTo(this.x)>0) {
                return -1;
            } else if (that.x == this.x) {
                if (that.y.compareTo(this.y)>0){
                    return -1;
                }else if(that.y.compareTo(this.y)<0){
                    return 1;
                }
                return 0;
            } else {
                return 1;
            }
        }
    }


    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

//        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            System.out.println(segment.toString());
            segment.draw();
        }
        StdDraw.show();

    }

}
