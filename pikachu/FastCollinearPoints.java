import java.util.*;

public class FastCollinearPoints {
	private ArrayList<LineSegment> segments4 = new ArrayList<LineSegment>();	
    public FastCollinearPoints(Point[] points) {
    	Point[] ps = points;
    	for (int i = 0; i < points.length; i++) {
    		Arrays.sort(ps, points[i].slopeOrder());
    		double sgm = 0.0;
    		Point pS = null;
    		Point pB = null;
    		int counter = 0;
    		if (points[i].compareTo(ps[0]) > 0) {
    			pB = points[i];
    			pS = ps[0];
    			sgm = points[i].slopeTo(ps[0]);
    		}
   			else if (points[i].compareTo(ps[0]) < 0) {
  				pB = ps[0];
  				pS = points[i];
  				sgm = points[i].slopeTo(ps[0]);
    		}
    		else {
    			pB = ps[0];
  				pS = points[i];
    		}
    		for (int j = 0; j < ps.length; j++) {
    			if (ps[j] != points[i]) {
    				if (sgm == points[i].slopeTo(ps[j])) {
    					counter++;
    					if (ps[j].compareTo(pB) > 0) {
    						pB = ps[j];
    					}
    					else if (ps[j].compareTo(pS) < 0) {
    						pS = ps[j];
    					}
    					if ((j == (ps.length - 1)) && counter >= 4) {
    						LineSegment s =new LineSegment(pS,pB);
    						if (!segments4.contains(s)) {
    							segments4.add(s);
    						}
    					}
    				}
    				else {
    					if (counter >= 4) {
    					    LineSegment s =new LineSegment(pS,pB);
    						if (!segments4.contains(s)) {
    							segments4.add(s);
    						}
    					}
    					if (points[i].compareTo(ps[j]) > 0) {
    					    pB = points[i];
    					    pS = ps[j];
    				    }
    				    else {
    					    pB = ps[j];
    					    pS = points[i];
    				    }
    					sgm = points[i].slopeTo(ps[j]);
    					counter = 1;
    				}
    			}
    			else {
    				if (counter >= 4) {
    					LineSegment s =new LineSegment(pS,pB);
    					if (!segments4.contains(s)) {
    						segments4.add(s);
    					}
    				}
    			}
    		}
    	}
    }    // finds all line segments containing 4 or more points
    public int numberOfSegments() {
    	int numOS = segments4.size();
    	return numOS;
    }        // the number of line segments
    public LineSegment[] segments() {
    	LineSegment[] segments = new LineSegment[segments4.size()];
    	segments = segments4.toArray(segments);
    	return segments;
    }               // the line segments
    public static void main(String[] args) {
    	int n = Integer.parseInt(args[0]);
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
        	int xi = (i*2)+1;
        	int yi = (i*2)+2;
            int x = Integer.parseInt(args[xi]);
            int y = Integer.parseInt(args[yi]);
            points[i] = new Point(x, y);
        }
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment s : collinear.segments()) {
        	System.out.println(s.toString());
        }
    }
}