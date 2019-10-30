/**
 * @author Sandeep Kolli. this class will print both the sorted arrays
 */
final class SortingTwoArrays {
    private SortingTwoArrays() {

    }

    public static void main(final String[] args) {
        final int three = 3;
        final int five = 5;
        final int seven = 7;
        int[] arr1 = { 1, 2, three };
        int[] arr2 = { three, five, seven };
        int i = 0;
        int j = 0;
        while (i + j < (2 * arr1.length)) {
            if (i == arr1.length) {
                System.out.print(arr2[j] + " ");
                j++;
            } else if (j == arr2.length) {
                System.out.print(arr1[i] + " ");
                i++;
            } else if (arr1[i] < arr2[j]) {
                System.out.print(arr1[i] + " ");
                i++;
            } else if (arr1[i] == arr2[j]) {
                System.out.print(arr1[i] + " ");
                System.out.print(arr2[j] + " ");
                i++;
                j++;
            } else {
                System.out.print(arr2[j] + " ");
                j++;
            }
        }
    }
}
