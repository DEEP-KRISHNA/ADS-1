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

            /**
             * Inserting elements into the array - O(N)
             */

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            System.out.println("How many largest elements you want to see : ");
            int k = sc.nextInt();
            if (k <= n) {

                /**
                 * for inserting k elements into MinPQ.
                 * 
                 * k elements and each element takes LOG(k).
                 * 
                 * so K*LOG(K) - complexity for following loop.
                 */
                for (int i = 0; i < k; i++) {
                    mpq.insert(arr[i]);
                }

                /**
                 * (N-K) elements left.
                 * 
                 * LOG(K-1) for deletion of each element.
                 * 
                 * LOG(k) for insertion of each element.
                 * 
                 * so 2(N-K)*LOG(K) - complexity for the following loop.
                 */
                for (int i = k; i < n; i++) {
                    if (mpq.minElement() < (double) arr[i]) {
                        mpq.delMin();
                        mpq.insert(arr[i]);
                    }
                }

                /**
                 * K times loop will iterate so complexity is O(K),
                 */
                System.out.println("The k largest elements in the array are : ");
                for (int i = 0; i < k; i++) {
                    System.out.println(mpq.delMin());
                }

                /**
                 * So if you add all
                 * 
                 * N + K*LOG(k) + 2(N-k)*LOG(K) +K
                 * 
                 * N + K + (2N - K)*LOG(K)
                 * 
                 * so overall complexity is (N*LOG(K))
                 */
            } else {
                System.out.println("K must be less than N");
            }
        } catch (Exception e) {
            System.out.println("Only int in accepted");
        }
    }
}