import edu.princeton.cs.algs4.*;

public class CriticalDis {
    public double dis = 0;
    public int n;
    private Point2D[] points;

    public CriticalDis(In in) {
        n = Integer.parseInt(in.readLine());
        MinPQ<PointPair> minPq = new MinPQ<>();
        int max = 0;
        int min = 0;
        double xymax = Double.NEGATIVE_INFINITY;
        double xymin = Double.POSITIVE_INFINITY;
        points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            double x;
            double y;
            String[] xAndY = in.readLine().split(" ");
            x = Double.parseDouble(xAndY[0]);
            y = Double.parseDouble(xAndY[1]);
            if (x + y > xymax) {
                xymax = x + y;
                max = i;
            }
            if (x + y < xymin) {
                xymin = x + y;
                min = i;
            }
            points[i] = new Point2D(x, y);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (points[i].x() < points[j].x() && points[i].y() < points[j].y()) {
                    minPq.insert(new PointPair(points[i], i, points[j], j));
                } else if (points[i].x() > points[j].x() && points[i].y() > points[j].y()) {
                    minPq.insert(new PointPair(points[j], j, points[i], i));
                }
            }
        }

        Digraph dg = new Digraph(n);
        PointPair pp = minPq.delMin();
        dg.addEdge(pp.vOfA, pp.vOfB);
        dis = pp.dis;
        BreadthFirstDirectedPaths bfdp = new BreadthFirstDirectedPaths(dg, min);
        while (!bfdp.hasPathTo(max)) {
            pp = minPq.delMin();
            dg.addEdge(pp.vOfA, pp.vOfB);
            dis = pp.dis;
            bfdp = new BreadthFirstDirectedPaths(dg, min);
        }

    }


    private class PointPair implements Comparable<PointPair> {
        private Point2D a;
        private Point2D b;
        public int vOfA;
        public int vOfB;
        public double dis;

        public PointPair(Point2D j, int v, Point2D k, int w) {
            double vx = j.x(), vy = j.y();
            double wx = k.x(), wy = k.y();
            a = new Point2D(vx, vy);
            b = new Point2D(wx, wy);
            dis = a.distanceTo(b);
            vOfA = v;
            vOfB = w;
        }

        public int compareTo(PointPair other) {
            double diff = this.dis - other.dis;
            if (diff > 0) {
                return 1;
            } else if (diff < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        CriticalDis cd = new CriticalDis(in);
        System.out.printf("%5.5f\n", cd.dis);
    }
}

