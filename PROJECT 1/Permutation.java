import java.util.NoSuchElementException;
import java.util.Scanner;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        try {
            RandomizedQueue<String> rq = new RandomizedQueue<String>();
            Scanner sc = new Scanner(System.in);
            int n = Integer.parseInt(args[0]);
            while (!(StdIn.isEmpty())) {
                rq.enqueue(StdIn.readString());
            }
            for (int i = 0; i < n; i++) {
                StdOut.println(rq.dequeue());
            }
        } catch (NoSuchElementException e) {

        } catch (IllegalArgumentException e) {

        }

    }

}