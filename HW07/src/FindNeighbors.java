import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Point2D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class FindNeighbors {
    public Node root;
    private MaxPQ<Point2D> maxPQ;
    public Node loca;
    private double vertidis;
    private double shortdis;

    private class Node {
        final int direction;
        Node left = null;
        Node right = null;
        Node previous = null;
        Point2D cen;

        private Node(int dir, Point2D p) {
            direction = dir;
            cen = new Point2D(p.x(), p.y());
        }

    }


    private void insert(Point2D p) {
        root = insert(root, 1, p);
    }

    private Node insert(Node x, int dir, Point2D p) {
        if (x == null) {
            return new Node(dir, p);
        }
        int cmp;
        switch (x.direction) {
            case 1:
                cmp = Point2D.X_ORDER.compare(p, x.cen);
                if (cmp < 0) {
                    x.left = insert(x.left, 0, p);
                    x.left.previous = x;
                } else if (cmp > 0) {
                    x.right = insert(x.right, 0, p);
                    x.right.previous = x;
                } else {
                    x.cen = p;
                }
                break;
            case 0:
                cmp = Point2D.Y_ORDER.compare(p, x.cen);
                if (cmp < 0) {
                    x.left = insert(x.left, 1, p);
                    x.left.previous = x;
                } else if (cmp > 0) {
                    x.right = insert(x.right, 1, p);
                    x.right.previous = x;
                } else {
                    x.cen = p;
                }
                break;
        }

        return x;
    }


    public void init(Point2D[] points) {
        for (int i = 0; i < points.length; i++) {
            insert(points[i]);
        }
        root.previous = null;
    }


    public Point2D[] query(Point2D p, int k) {
        maxPQ = new MaxPQ<>(p.distanceToOrder());
        Point2D[] result = new Point2D[k];

        quering(root, p, k);

        for (int i = 0; i < k; i++) {
            result[i] = maxPQ.delMax();
        }
        return result;
    }

    private void quering(Node top, Point2D p, int k) {
        if (top==null){return;}
        goThoughBelow(top, p);

        //update shortdis
        maxPQ = cleanDuplicate(maxPQ);
        Point2D temp;
        int size = maxPQ.size();
        System.out.println(size);
        if (size < k) {
            shortdis = Double.POSITIVE_INFINITY;
        } else if (size > k) {
            for (int i=0;i<size-k;i++){
                maxPQ.delMax();
            }
            temp=maxPQ.delMax();
            maxPQ.insert(temp);
            shortdis = temp.distanceTo(p);
        } else {
            temp = maxPQ.delMax();
            maxPQ.insert(temp);
            shortdis = temp.distanceTo(p);
        }
        System.out.println(maxPQ.size());


        //update vertidis
        switch (loca.direction) {
            case 1:
                vertidis = Math.abs(p.x() - loca.cen.x());
                break;
            case 0:
                vertidis = Math.abs(p.y() - loca.cen.y());
                break;
        }

        //compare shortdis and vertidis
        if (shortdis > vertidis) {
            int position = 0;
            int cmp = 0;
            switch (top.direction) {
                case 1:
                    cmp = Point2D.X_ORDER.compare(p, top.cen);
                    break;
                case 0:
                    cmp = Point2D.Y_ORDER.compare(p, top.cen);
                    break;
            }
            position = cmp > 0 ? 1 : -1;
            switch (position) {
                case 1:
                    quering(top.left, p, k);
                    break;
                case -1:
                    quering(top.right, p, k);
                    break;
            }
        }
        if (top.previous == null) {
            return;
        }
        quering(top.previous, p, k);
    }

    private void goThoughBelow(Node x, Point2D p) {
        if (x == null) {
            return;
        }
        loca = x;
        maxPQ.insert(x.cen);
        int cmp;
        switch (x.direction) {
            case 1:
                cmp = Point2D.X_ORDER.compare(p, x.cen);
                if (cmp < 0) {
                    goThoughBelow(x.left, p);
                } else if (cmp > 0) {
                    goThoughBelow(x.right, p);
                } else {
                }
                break;
            case 0:
                cmp = Point2D.Y_ORDER.compare(p, x.cen);
                if (cmp < 0) {
                    goThoughBelow(x.left, p);
                } else if (cmp > 0) {
                    goThoughBelow(x.right, p);
                } else {
                }
                break;
        }
        return;
    }
    public MaxPQ<Point2D> cleanDuplicate(MaxPQ<Point2D> pq){
        ArrayList<Point2D> tem = new ArrayList<>();
        int size = pq.size();
        for (int i = 0; i <size; i++) {
          tem.add(pq.delMax());
        }
        HashSet<Point2D> set =new HashSet<Point2D>(tem);
        tem = new ArrayList<>(set);
        size = tem.size();
        for (int i = 0; i <size; i++) {
            pq.insert(tem.get(i));
        }
        return pq;
    }

    public static void main(String[] args) {
        Point2D[] points = new Point2D[6];
        points[0] = new Point2D(6, 4);
        points[1] = new Point2D(8, 3);
        points[2] = new Point2D(4, 6);
        points[3] = new Point2D(3, 2);
        points[4] = new Point2D(2, 5);
        points[5] = new Point2D(5, 7);
        FindNeighbors fn = new FindNeighbors();
        fn.init(points);

        Point2D query = new Point2D(1, 6.6);

        Point2D[] re = fn.query(query, 2);
        System.out.println(re[0]);
        System.out.println(re[1]);


//        MaxPQ<Point2D> a = new MaxPQ<>(query.distanceToOrder());

//        System.out.println(a.size());
//        for (int i=0;i<a.size()-1;i++){
//            a.delMax();
//        }
//        System.out.println(a.size());
//        System.out.println(a.delMax());
    }

}
