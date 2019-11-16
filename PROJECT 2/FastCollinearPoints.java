import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lines = new ArrayList<LineSegment>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        Point[] newPoints = points;
        for (int i = 0; i < newPoints.length; i++) {
            Arrays.sort(newPoints);
            Point org = newPoints[i];
            Arrays.sort(newPoints, org.slopeOrder());
            System.out.println("for " + i + " loop " + org.toString());
            for (int j = 0; j < newPoints.length; j++) {
                System.out.println(newPoints[j].toString() + " " + org.slopeTo(newPoints[j]));
            }

            HashMap<Double, Integer> hash = new HashMap<Double, Integer>();
            for (int j = 0; j < newPoints.length; j++) {
                if (hash.containsKey(newPoints[i].slopeTo(newPoints[j]))) {
                    hash.put(newPoints[i].slopeTo(newPoints[j]), hash.get(newPoints[i].slopeTo(newPoints[j])) + 1);
                } else {
                    hash.put(newPoints[i].slopeTo(newPoints[j]), 1);
                }
            }
            ArrayList<Double> keysFin = new ArrayList<Double>();
            Iterator keyIt = hash.keySet().iterator();
            System.out.println();
            while (keyIt.hasNext()) {
                double temp = (double) keyIt.next();
                if (hash.get(temp) >= 3) {
                    keysFin.add(temp);
                }
            }
            double tk = -969.9999;
            for (int j = 0; j < newPoints.length; j++) {
                if (keysFin.contains(newPoints[i].slopeTo(newPoints[j]))) {
                    if (tk != newPoints[i].slopeTo(newPoints[j])) {
                        tk = newPoints[i].slopeTo(newPoints[j]);
                        System.out.println();
                        System.out.println(newPoints[i]);
                        System.out.println(newPoints[j]);
                    } else {
                        System.out.println(newPoints[j]);
                    }
                }
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