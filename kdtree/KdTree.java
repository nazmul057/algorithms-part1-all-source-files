
// import java.util.Iterator;


import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

public class KdTree {

    private class MyBST {
        private Node root;
        private Point2D nearest;
        private double nearestDistance;
        private int totalNodes;

        private MyBST() {
            this.totalNodes = 0;
        }

        private class Node {
            private double key;
            private Point2D val;
            // private Value valY;
            private Node left, right;
            private Node(double key, Point2D val) {
                this.key = key;
                this.val = val;
                // this.valY = receivedValY;
            }
        }

        private Node recursivePut(Node n, double keyX, double keyY, Point2D val, boolean partitionByX) {
            if (n == null) {
                if (partitionByX) {
                    this.totalNodes += 1;
                    return new Node(keyX, val);
                }
                else {
                    this.totalNodes += 1;
                    return new Node(keyY, val);
                }
            }

            if (keyX == n.val.x() && keyY == n.val.y()) return n;

            int cmp;
            if (partitionByX) {
                // cmp = keyX.compareTo(x.key);
                if (keyX < n.key) cmp = - 5;
                else if (keyX > n.key) cmp = 5;
                else cmp = 0;
            }
            else {
                // cmp = keyY.compareTo(x.key);
                if (keyY < n.key) cmp = - 5;
                else if (keyY > n.key) cmp = 5;
                else cmp = 0;
            }
            // Key updatedKey;
            // if (partitionByX) updatedKey =
            if (cmp < 0) {
                // if (partitionByX) x.left = deepPut(x.left, keyX, keyY, val, false);
                // else x.left = put(x.left, key, val, true);
                n.left = recursivePut(n.left, keyX, keyY, val, !partitionByX);
            }
            else
                n.right = recursivePut(n.right, keyX, keyY, val, !partitionByX);
            // else // if (cmp == 0)
            //     n.val = val;

            return n;
        }

        private void put(double keyX, double keyY, Point2D val) {
            // Key keyX;
            // Key keyY;
            this.root = recursivePut(this.root, keyX, keyY, val, true);
        }

        private Point2D get(double keyX, double keyY) {
            Node n = this.root;
            boolean partitionByX = true;
            while (n != null)    {
                int cmp;
                if (partitionByX) {
                    // cmp = keyX.compareTo(x.key);
                    if (keyX < n.key) cmp = - 5;
                    else if (keyX > n.key) cmp = 5;
                    else cmp = 0;
                }
                else {
                    // cmp = keyY.compareTo(x.key);
                    if (keyY < n.key) cmp = - 5;
                    else if (keyY > n.key) cmp = 5;
                    else cmp = 0;
                }
                // int cmp = key.compareTo(x.key);
                if      (cmp  < 0) n = n.left;
                else if (cmp  > 0) n = n.right;
                else {// if  (cmp == 0)
                    if (keyX == n.val.x() && keyY == n.val.y())
                        return n.val;
                    else n = n.right;
                }

                partitionByX = !partitionByX;
            }
            return null;
        }


        // private void delete(Key key) {

        // }

        private void recursiveGetAllrectVal(Node n, RectHV rect, Stack<Point2D> s, boolean partitionedByX) {
            if (n == null) { return; }

            if (partitionedByX) {
                if (rect.contains(n.val)) {
                    s.push(n.val);
                    recursiveGetAllrectVal(n.left, rect, s, !partitionedByX);
                    recursiveGetAllrectVal(n.right, rect, s, !partitionedByX);
                }
                else if (n.val.x() < rect.xmin()) recursiveGetAllrectVal(n.right, rect, s, !partitionedByX);
                else if (n.val.x() > rect.xmax()) recursiveGetAllrectVal(n.left, rect, s, !partitionedByX);
                else {
                    recursiveGetAllrectVal(n.left, rect, s, !partitionedByX);
                    recursiveGetAllrectVal(n.right, rect, s, !partitionedByX);
                }
            }

            else {
                if (rect.contains(n.val)) {
                    s.push(n.val);
                    recursiveGetAllrectVal(n.left, rect, s, !partitionedByX);
                    recursiveGetAllrectVal(n.right, rect, s, !partitionedByX);
                }
                else if (n.val.y() < rect.ymin()) recursiveGetAllrectVal(n.right, rect, s, !partitionedByX);
                else if (n.val.y() > rect.ymax()) recursiveGetAllrectVal(n.left, rect, s, !partitionedByX);
                else {
                    recursiveGetAllrectVal(n.left, rect, s, !partitionedByX);
                    recursiveGetAllrectVal(n.right, rect, s, !partitionedByX);
                }
            }

        }


        private Iterable<Point2D> getAllrectVal(RectHV rect) {
            Stack<Point2D> s = new Stack<Point2D>();
            recursiveGetAllrectVal(this.root, rect, s, true);
            return s;
        }

        private void recursiveGetNearestValue(Node n, Point2D p, boolean partitionedByX) {
            if (n == null) return;

            if (n.val.distanceSquaredTo(p) < this.nearestDistance) {
                this.nearest = n.val;
                this.nearestDistance = n.val.distanceSquaredTo(p);
            }

            if (partitionedByX) {
                if (p.x() < n.val.x()) {
                    recursiveGetNearestValue(n.left, p, !partitionedByX);
                    // 1 if (!(this.nearest.x() < n.val.x())) recursiveGetNearestValue(n.right, p, !partitionedByX);
                    // 2 if (!((n.val.x() - p.x()) > (n.val.x() - this.nearest.x())))
                    // 2     recursiveGetNearestValue(n.right, p, !partitionedByX);
                    if (!((n.val.x() - p.x()) > this.nearest.distanceTo(p)))
                        recursiveGetNearestValue(n.right, p, !partitionedByX);
                    // System.out.println((!((n.val.x() - p.x()) > this.nearest.distanceTo(p))));
                } else if (p.x() > n.val.x()) {
                    recursiveGetNearestValue(n.right, p, !partitionedByX);
                    // 1 if (!(this.nearest.x() > n.val.x())) recursiveGetNearestValue(n.left, p, !partitionedByX);
                    // 2 if (!((p.x() - n.val.x()) > (this.nearest.x() - n.val.x())))
                    // 2    recursiveGetNearestValue(n.left, p, !partitionedByX);
                    if (!((p.x() - n.val.x()) > this.nearest.distanceTo(p)))
                        recursiveGetNearestValue(n.left, p, !partitionedByX);
                    // System.out.println((!((p.x() - n.val.x()) > this.nearest.distanceTo(p))));
                } else {
                    recursiveGetNearestValue(n.left, p, !partitionedByX);
                    recursiveGetNearestValue(n.right, p, !partitionedByX);
                }
            }

            else {
                if (p.y() < n.val.y()) {
                    recursiveGetNearestValue(n.left, p, !partitionedByX);
                    // 1 if (!(this.nearest.y() < n.val.y())) recursiveGetNearestValue(n.right, p, !partitionedByX);
                    // 2 if (!((n.val.y() - p.y()) > (n.val.y() - this.nearest.y())))
                    // 2    recursiveGetNearestValue(n.right, p, !partitionedByX);
                    if (!((n.val.y() - p.y()) > this.nearest.distanceTo(p)))
                        recursiveGetNearestValue(n.right, p, !partitionedByX);
                    // System.out.println((!((n.val.y() - p.y()) > this.nearest.distanceTo(p))));
                } else if (p.y() > n.val.y()) {
                    recursiveGetNearestValue(n.right, p, !partitionedByX);
                    // 1 if (!(this.nearest.y() > n.val.y())) recursiveGetNearestValue(n.left, p, !partitionedByX);
                    // 2 if (!((p.y() - n.val.y()) > (this.nearest.y() - n.val.y())))
                    // 2    recursiveGetNearestValue(n.left, p, !partitionedByX);
                    if (!((p.y() - n.val.y()) > this.nearest.distanceTo(p)))
                        recursiveGetNearestValue(n.left, p, !partitionedByX);
                    // System.out.println((!((p.y() - n.val.y()) > this.nearest.distanceTo(p))));
                } else {
                    recursiveGetNearestValue(n.left, p, !partitionedByX);
                    recursiveGetNearestValue(n.right, p, !partitionedByX);
                }
            }

        }

        private Point2D getNearestValue(Point2D p) {
            if (this.totalNodes > 0) {
                if (this.get(p.x(), p.y()) != null) return p;
                else {
                    this.nearest = this.root.val;
                    this.nearestDistance = nearest.distanceSquaredTo(p);
                    recursiveGetNearestValue(this.root, p, true);
                    return this.nearest;
                }
            }
            else return null;
        }

        private void deepInorder(Node x, Queue<Point2D> q) {
            if (x == null) return;
            deepInorder(x.left, q);
            q.enqueue(x.val);
            deepInorder(x.right, q);
        }

        private Iterable<Point2D> iterator() {
            Queue<Point2D> q = new Queue<Point2D>();
            deepInorder(root, q);
            return q;
        }
    }

    private MyBST allPoints;
    // private int pointsCount;
    public         KdTree() {                               // construct an empty set of points
        this.allPoints = new MyBST();
        // this.pointsCount = 0;
    }
    public           boolean isEmpty() {                      // is the set empty?
        return this.allPoints.totalNodes == 0;
    }
    public               int size() {                         // number of points in the set
        return this.allPoints.totalNodes;
    }
    public              void insert(Point2D p) {              // add the point to the set (if it is not already in the set)
        if (p == null) throw new IllegalArgumentException("Argument cannot be null");
        // if (!this.contains(p)) {
            this.allPoints.put(p.x(), p.y(), p);
            // this.pointsCount += 1;
        // }
    }
    public           boolean contains(Point2D p) {            // does the set contain point p?
        if (p == null) throw new IllegalArgumentException("Argument cannot be null");
        return this.allPoints.get(p.x(), p.y()) != null;
    }
    public              void draw() {                         // draw all points to standard draw

        Iterable<Point2D> myIterator = this.allPoints.iterator();

        for (Point2D it : myIterator) {
            it.draw();
            // Point2D z = it.next();
            // z.draw();
        }
    }

    // private void getAllrectVal(Node x, double maxX, double maxY, double minX, double minY) {

    // }

    public Iterable<Point2D> range(RectHV rect) {             // all points that are inside the rectangle (or on the boundary)
        if (rect == null) throw new IllegalArgumentException("Argument cannot be null");
        return this.allPoints.getAllrectVal(rect);
    }
    public           Point2D nearest(Point2D p) {             // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) throw new IllegalArgumentException("Argument cannot be null");
        return this.allPoints.getNearestValue(p);
    }

    public static void main(String[] args) {                  // unit testing of the methods (optional)
        /*
        In in = new In("inputPoints.txt");
        // PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            // brute.insert(p);
        }
        Iterable<Point2D> myIterator = kdtree.allPoints.iterator();

        int count = 0;
        for (Point2D i : myIterator) {
            StdOut.println(i.toString());
            count ++;
        }
        StdOut.println(count);
        StdOut.println(true && true);



        KdTree testTree = new KdTree();
        Point2D pa = new Point2D(0.7,0.2);
        Point2D pb = new Point2D(0.5,0.4);
        Point2D pc = new Point2D(0.2,0.3);
        Point2D pd = new Point2D(0.4, 0.7);
        Point2D pe = new Point2D(0.9, 0.6);

        Point2D pf = new Point2D(0.2, 0.31);

        testTree.insert(pa);
        testTree.insert(pb);
        testTree.insert(pc);
        testTree.insert(pd);
        testTree.insert(pe);


        StdOut.println(testTree.size());
        StdOut.println(testTree.contains(pf));
        // StdOut.println();

        */
    }
}


