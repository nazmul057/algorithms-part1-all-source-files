import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

// import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class PointSET {

    private SET<Point2D> allPoints;

    public         PointSET() {                              // construct an empty set of points
        this.allPoints = new SET<>();
    }
    public           boolean isEmpty() {                     // is the set empty?
        return this.allPoints.size() == 0;
    }
    public               int size() {                        // number of points in the set
        return this.allPoints.size();
    }
    public              void insert(Point2D p) {             // add the point to the set (if it is not already in the set)
        if (p == null) throw new IllegalArgumentException("Argument cannot be null");
        this.allPoints.add(p);
    }
    public           boolean contains(Point2D p) {            // does the set contain point p?
        if (p == null) throw new IllegalArgumentException("Argument cannot be null");
        return allPoints.contains(p);
    }
    public              void draw() {                         // draw all points to standard draw
        for (Iterator<Point2D> it = this.allPoints.iterator(); it.hasNext(); ) {
            it.next().draw();
            // Point2D z = it.next();
            // z.draw();
        }
    }
    public Iterable<Point2D> range(RectHV rect) {             // all points that are inside the rectangle (or on the boundary)
        if (rect == null) throw new IllegalArgumentException("Argument cannot be null");
        double xMin = rect.xmin();
        double yMin = rect.ymin();

        double xMax = rect.xmax();
        double yMax = rect.ymax();


        Stack<Point2D> toBeReturned = new Stack<>();

        for (Iterator<Point2D> it = this.allPoints.iterator(); it.hasNext(); ) {
            Point2D currentPoint = it.next();
            if (currentPoint.x() <= xMax &&
                    currentPoint.x() >= xMin &&
                    currentPoint.y() <= yMax &&
                    currentPoint.y() >= yMin
            )
                toBeReturned.push(currentPoint);
        }

        return toBeReturned;

    }
    public           Point2D nearest(Point2D p) {             // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) throw new IllegalArgumentException("Argument cannot be null");
        Point2D closest = null;

        for (Iterator<Point2D> it = this.allPoints.iterator(); it.hasNext(); ) {
            Point2D now = it.next();

            if (p.equals(now)) continue;

            if (closest == null) closest = now;
            else if (p.distanceSquaredTo(now) < p.distanceSquaredTo(closest)) closest = now;
        }

        return closest;

    }

    public static void main(String[] args) {                  // unit testing of the methods (optional)
    }
}

