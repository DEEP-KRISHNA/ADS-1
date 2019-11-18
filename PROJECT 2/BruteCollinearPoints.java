import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class BruteCollinearPoints {

    private ArrayList<LineSegment> lines = new ArrayList<LineSegment>();

    // finds all line segments containing 4 points.
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] pointsList = points.clone();
        for (int i = 0; i < pointsList.length; i++) {
            if (pointsList[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        boolean condition1;
        boolean condition2;
        boolean condition3;
        Arrays.sort(pointsList);
        for (int i = 0; i < pointsList.length - 1; i++) {
            if (pointsList[i].compareTo(pointsList[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < pointsList.length - 3; i++) {
            for (int j = i + 1; j < pointsList.length - 2; j++) {
                for (int k = j + 1; k < pointsList.length - 1; k++) {
                    for (int l = k + 1; l < pointsList.length; l++) {
                        if (pointsList[i] != null && pointsList[j] != null && pointsList[k] != null
                                && pointsList[l] != null) {
                            condition1 = ((pointsList[i].slopeTo(pointsList[j])) == (pointsList[j]
                                    .slopeTo(pointsList[k])));
                            condition2 = ((pointsList[j].slopeTo(pointsList[k])) == (pointsList[k]
                                    .slopeTo(pointsList[l])));
                            condition3 = ((pointsList[i].compareTo(pointsList[j]) != 0)
                                    && (pointsList[j].compareTo(pointsList[k]) != 0)
                                    && (pointsList[k].compareTo(pointsList[l]) != 0));
                            if (condition1 && condition2 && condition3) {
                                lines.add(new LineSegment(pointsList[i], pointsList[l]));
                            }
                        }
                    }
                }
            }
        }
    }

    // the number of line segments.
    public int numberOfSegments() {
        return lines.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] newLines = new LineSegment[lines.size()];+
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}