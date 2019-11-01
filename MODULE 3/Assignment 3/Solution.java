/**
 * in this class we will be implementing both selection and insertion sort.
 * 
 * @author Sandeep Kolli.
 */
class Solution {

	/**
	 * The following function will do sorting based on insertion sort algo.
	 *
	 * @param arr the array which need to be sorted
	 * @return the sorted array
	 */
	public int[] sortInsertion(final int[] arr) {
		// fill you code Here
		int temp;
		if (arr != null) {
			for (int i = 1; i < arr.length; i++) {
				for (int j = i; j > 0; j--) {
					if (arr[j] < arr[j - 1]) {
						temp = arr[j];
						arr[j] = arr[j - 1];
						arr[j - 1] = temp;
					}
				}
			}
			return arr;
		} else {
			return null;
		}
	}

	/**
	 * The following function will do sorting based on selection sort algo.
	 *
	 * @param arr the array which need to be sorted
	 * @return the sorted array
	 */
	public int[] sortSelection(final int[] arr) {
		// fill you code Here
		int temp;
		int min;
		if (arr.length != 0) {
			for (int i = 0; i < arr.length; i++) {
				min = i;
				for (int j = i; j < arr.length; j++) {
					if (arr[j] < arr[min]) {
						min = j;
					}
				}
				temp = arr[i];
				arr[i] = arr[min];
				arr[min] = temp;
			}
			return arr;
		} else {
			return null;
		}
	}
}
