/**
 * @author Sandeep Kolli. This is an optimised way to do the 3 sum problem.
 */
public final class ThreeSum {
    private ThreeSum() {

    }

    public static void main(final String[] args) {
        final int[] arr = { -40, -20, -10, 0, 5, 10, 30, 40 };
        int sumOfTwo = 0;
        int counter = 0;
        int match = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                match = -1;
                sumOfTwo = -(arr[i] + arr[j]);
                int hi = arr.length - 1;
                int lo = 0;
                while (lo <= hi) {
                    int mid = lo + ((hi - lo) / 2);
                    if (sumOfTwo < arr[mid]) {
                        hi = mid - 1;
                    } else if (sumOfTwo > arr[mid]) {
                        lo = mid + 1;
                    } else {
                        if ((arr[i] < arr[j]) && ((arr[j] < arr[mid]))) {
                            match = 1;
                            break;
                        } else {
                            break;
                        }
                    }
                }
                if (match == 1) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
}
