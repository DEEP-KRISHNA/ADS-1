/**
 * the following is the implementation of merge sort.
 *
 * @author Sandeep Kolli.
 */
final class Solution {

	private Solution() {

	}

	/**
	 * The following function will merge 2 arrays.
	 *
	 * @param a   actual array.
	 * @param aux auxiallary array, for intermediate process.
	 * @param lo  lowest index.
	 * @param mid middle index.
	 * @param hi  highest index.
	 */
	private static void merge(final Comparable[] a, final Comparable[] aux, final int lo, final int mid, final int hi) {
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) {
				a[k] = aux[i++];
			} else if (aux[j].compareTo(aux[i]) < 0) {
				a[k] = aux[j++];
			} else {
				a[k] = aux[i++];
			}
		}
	}

	/**
	 * the following function gets called in mergeSort function. the function will
	 * divide into 2 halves gets it sorted and finally merges.
	 *
	 * @param a   actual array.
	 * @param aux auxillary array.
	 * @param lo  lowest index.
	 * @param hi  highest index.
	 */
	private static void sort(final Comparable[] a, final Comparable[] aux, final int lo, final int hi) {
		if (hi > lo) {
			int mid = lo + (hi - lo) / 2;
			sort(a, aux, lo, mid);
			sort(a, aux, mid + 1, hi);
			merge(a, aux, lo, mid, hi);
		}
	}

	/**
	 * the main function of this class. initial call happens in this function.
	 *
	 * @param arr actual array to get sorted
	 * @return the sorted array gets returned.
	 */
	public static Comparable[] mergeSort(final Comparable[] arr) {

		Comparable[] aux = new Comparable[arr.length];
		sort(arr, aux, 0, arr.length - 1);
		return arr;
	}
}
