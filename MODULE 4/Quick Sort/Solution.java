/**
 * The folling class is the implementation of quick sort.
 *
 * @author Sandeep Kolli.
 */
final class Solution {
	private Solution() {

	}

	/**
	 * the lowest index element is placed correctly. then do partion into 2 halves
	 * according the correctly placed value. the right side partioned array containd
	 * the elements which are higher the partioned array value. the left side
	 * partioned array containd the elements which are lower the partioned array
	 * value.
	 *
	 * @param a  actual array.
	 * @param lo lowest index.
	 * @param hi highest index.
	 * @return the partioned index.
	 */
	private static int partition(final int[] a, final int lo, final int hi) {
		int i = lo;
		int j = hi + 1;
		int temp;
		while (true) {
			while (a[++i] < a[lo]) {
				if (i == hi) {
					break;
				}
			}
			while (a[--j] > a[lo]) {
				if (j == lo) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
		temp = a[lo];
		a[lo] = a[j];
		a[j] = temp;
		return j;
	}

	/**
	 * this function will get called in QuickSort function. this function will get
	 * called recursively untill all elements get placed in correct order.
	 *
	 * @param arr actual array.
	 * @param lo  lowest index.
	 * @param hi  highest index.
	 */
	private static void sort(final int[] arr, final int lo, final int hi) {
		if (hi > lo) {
			int j = partition(arr, lo, hi);
			sort(arr, lo, j - 1);
			sort(arr, j + 1, hi);
		}
	}

	/**
	 * the intial call happens in this function. the final value returned back.
	 *
	 * @param arr actual array.
	 * @return returnes the sorted array.
	 */

	public static int[] quickSort(final int[] arr) {
		sort(arr, 0, arr.length - 1);
		return arr;
	}

}
