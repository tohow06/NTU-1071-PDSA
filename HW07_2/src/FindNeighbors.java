import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.MinPQ;
public class FindNeighbors {
    private Point2D[] p;
    public void init(Point2D[] points){
        p=new Point2D[points.length];
        p=points;
    }

    public Point2D[] query(Point2D point, int k){
        Point2D[] result= new Point2D[k];
        MinPQ<Point2D> minPQ = new MinPQ<>(point.distanceToOrder());
        for (Point2D s :
                p) {
            minPQ.insert(s);
        }
        for (int i=0;i<k;i++){
            result[i]=minPQ.delMin();
        }
       return result;
    }
}
