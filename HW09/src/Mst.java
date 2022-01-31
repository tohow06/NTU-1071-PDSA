import edu.princeton.cs.algs4.*;

public class Mst {
    public double wight;
    public Point2D[] points;

    public Mst(In in) {
        int n = Integer.parseInt(in.readLine());
        points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            String[] xAndY = in.readLine().split(" ");
            points[i] = new Point2D(Double.parseDouble(xAndY[0]), Double.parseDouble(xAndY[1]));
        }
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ewg.addEdge(new Edge(i, j, points[i].distanceTo(points[j])));
            }
        }
        KruskalMST kmst = new KruskalMST(ewg);
        wight = kmst.weight();
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        Mst m = new Mst(in);

        System.out.printf("%5.5f\n", m.wight);


    }
}
