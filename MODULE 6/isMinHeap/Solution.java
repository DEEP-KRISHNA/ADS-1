/**
 * check whether an array pq[] is a min-oriented heap.
 *
 * @author Sandeep Kolli.
 */
final class Solution {
	private Solution() {

	}

	/**
	 * Validation is done in the following function.
	 *
	 * @param arr the array need to validated.
	 * @return true if it is a min - oriented heap.
	 */
	public static boolean isMinHeap(final double[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if ((2 * i) < arr.length && ((2 * i) + 1) < arr.length) {
				if (!((arr[i] <= arr[2 * i]) && arr[i] <= arr[(2 * i) + 1])) {
					return false;
				}
			} else if ((2 * i) < arr.length) {
				if (!((arr[i] <= arr[2 * i]))) {
					return false;
				}
			}
		}
		return !(arr.length == 0);
	}
}
