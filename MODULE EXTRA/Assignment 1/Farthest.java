
/**
 * @author Sandeep Kolli (2019501035).
 */
import java.util.Scanner;

public class Farthest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many elements you want in array : ");
        try {
            int n = sc.nextInt(); // Take input for number of elements.
            double[] arr = new double[n];
            System.out.println("Enter the elements in the array : ");
            for (int i = 0; i < n; i++) {
                double temp = sc.nextDouble(); // Take input for each element.
                arr[i] = temp;
            }
            if (n >= 0) {
                double max = arr[0];
                double min = arr[0];
                for (int i = 0; i < n; i++) {
                    if (arr[i] < min) {
                        min = arr[i];// min value gets updated if it find new min.
                    }
                    if (arr[i] > max) {
                        max = arr[i];// max value gets updated if it find new max.
                    }
                }
                System.out.println("The max element in the array is : " + max);
                System.out.println("The min element in the array is : " + min);
                double disp = max - min;
                System.out.println("The max difference possible in the given array : " + disp);
            } else {
                System.out.println("Array size must be greater than zero");
            }

        } catch (Exception e) {
            System.out.println("int or double is expected");
        }
    }
}