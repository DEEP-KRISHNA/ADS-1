/**
 * @author Sandeep Kolli. this class does binary search, prints number of common
 *         pairs.
 */
final class BinarySearch {
    private BinarySearch() {

    }

    /**
     * in the follwing main elements are searched in binary search method.
     * prerequisite for this is already elements should be in sorted order.
     * 
     * @param args inline args
     */
    public static void main(final String[] args) {
        final int one = 1;
        final int two = 2;
        final int three = 3;
        final int four = 4;
        int count = 0;
        int[] arr = { one, one, two, three, three, four };
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                count++;
            }
        }
        System.out.println(count);
    }
}
