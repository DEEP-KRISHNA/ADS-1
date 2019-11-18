import java.util.Scanner;

public class KLargest {
    public static void main(String[] args) {
        MinPQ mpq = new MinPQ();
        System.out.println("How many Elements you want to enter : ");
        Scanner sc = new Scanner(System.in);
        try {
            int n = sc.nextInt();
            int[] arr = new int[n];
            System.out.println("Enter elements into the array : ");
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.println("How many largest elements you want to see : ");
            int k = sc.nextInt();
            if (k <= n) {
                for (int i = 0; i < k; i++) {
                    mpq.insert(arr[i]);
                }
                for (int i = k; i < n; i++) {
                    if (mpq.minElement() < (double) arr[i]) {
                        mpq.delMin();
                        mpq.insert(arr[i]);
                    }
                }

                System.out.println("The k largest elements in the array are : ");
                for (int i = 0; i < k; i++) {
                    System.out.println(mpq.delMin());
                }
            } else {
                System.out.println("K must be less than N");
            }
        } catch (Exception e) {
            System.out.println("Only int in accepted");
        }
    }
}