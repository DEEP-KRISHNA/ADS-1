import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lines = new ArrayList<LineSegment>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] newPoints = points.clone();

        for (int i = 0; i < newPoints.length; i++) {
            if (newPoints[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(newPoints);
        for (int i = 0; i < newPoints.length - 1; i++) {
            if (newPoints[i].compareTo(newPoints[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < newPoints.length; i++) {
            Arrays.sort(newPoints);
            Point org = newPoints[i];
            Arrays.sort(newPoints, org.slopeOrder());
            ArrayList<Point> fourPoints = new ArrayList<Point>();
            fourPoints.add(org);
            for (int j = 0; j < newPoints.length - 1; j++) {
                if ((org.slopeTo(newPoints[j])) == (org.slopeTo(newPoints[j + 1]))) {
                    fourPoints.add(newPoints[j]);
                    fourPoints.add(newPoints[j + 1]);
                } else {
                    if (fourPoints.size() >= 5) {
                        Collections.sort(fourPoints);
                        lines.add(new LineSegment(fourPoints.get(0), fourPoints.get(fourPoints.size() - 1)));
                        fourPoints = new ArrayList<Point>();
                        fourPoints.add(org);
                    } else {
                        fourPoints = new ArrayList<Point>();
                        fourPoints.add(org);
                    }
                }
            }
            if (fourPoints.size() >= 5) {
                Collections.sort(fourPoints);
                lines.add(new LineSegment(fourPoints.get(0), fourPoints.get(fourPoints.size() - 1)));
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lines.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] newLines = new LineSegment[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            newLines[i] = lines.get(i);
        }
        return newLines;
    }

    public static void main(String[] args) {

        // read the n points from a file
        // StdIn in = new StdIn(args[0]);
        int n = StdIn.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
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
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}