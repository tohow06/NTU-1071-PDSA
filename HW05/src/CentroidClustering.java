import edu.princeton.cs.algs4.*;

import java.util.ArrayList;


public class CentroidClustering {
    private ArrayList<Cluster> listCluster = new ArrayList<>();
    private MaxPQ<Cluster> clusterPQ = new MaxPQ<>();
    private MinPQ<clusterPair> pairsPQ = new MinPQ<>();

    public class clusterPair implements Comparable<clusterPair> {
        public final Cluster a;
        public final Cluster b;
        private double distance;

        public clusterPair(Cluster a, Cluster b) {
            this.a = a;
            this.b = b;
            distance = getDistance(this.a, this.b);
        }

        @Override
        public int compareTo(clusterPair that) {
            if (this.distance > that.distance) {
                return 1;
            } else if (this.distance < that.distance) {
                return -1;
            } else {
                return 0;
            }
        }

        private double getDistance(Cluster a, Cluster b) {
            double x = b.calCentroid().x() - a.calCentroid().x();
            double y = b.calCentroid().y() - a.calCentroid().y();
            return Math.pow(x * x + y * y, 0.5);
        }

    }

    public class Cluster implements Comparable<Cluster> {
        public ArrayList<Point2D> listPoint = new ArrayList<Point2D>();

        public void insert(Point2D p) {
            listPoint.add(p);
        }

        private Point2D calCentroid() {
            double x = 0;
            double y = 0;
            for (int i = 0; i < size(); i++) {
                x += listPoint.get(i).x();
                y += listPoint.get(i).y();
            }
            return new Point2D(x / size(), y / size());
        }

        public int size() {
            return this.listPoint.size();
        }

        public int compareTo(Cluster that) {
            return this.size() - that.size();
        }
    }


    public CentroidClustering(Point2D[] points) {
        for (int i = 0; i < points.length; i++) {
            listCluster.add(new Cluster());
            listCluster.get(i).insert(points[i]);
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                pairsPQ.insert(new clusterPair(listCluster.get(i), listCluster.get(j)));
            }
        }
        checkAndMerge();
    }

    public String getMax() {
        Cluster cl = clusterPQ.delMax();
        return String.format("%d %.2f %.2f", cl.size(), cl.calCentroid().x(), cl.calCentroid().y());
    }

    private void checkAndMerge() {
        while (listCluster.size() > 3) {
            clusterPair minPair = pairsPQ.delMin();
            int a = listCluster.indexOf(minPair.a);
            int b = listCluster.indexOf(minPair.b);
            if (a == -1 || b == -1 || a == b) {
                continue;
            }
            listCluster.add(new Cluster());
            int c = listCluster.size();
            listCluster.get(c-1).listPoint.addAll(listCluster.get(a).listPoint);
            listCluster.get(c-1).listPoint.addAll(listCluster.get(b).listPoint);
            listCluster.remove(a);
            if (a<b){b--;}
            listCluster.remove(b);
            c=listCluster.size();
            for (int i = 0; i < c; i++) {
                pairsPQ.insert(new clusterPair(listCluster.get(c-1), listCluster.get(i)));
            }
        }
        for (Cluster cc :
                listCluster) {
            clusterPQ.insert(cc);
        }
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        CentroidClustering cc = new CentroidClustering(points);

        System.out.println(cc.getMax());
        System.out.println(cc.getMax());
        System.out.println(cc.getMax());
    }

}


